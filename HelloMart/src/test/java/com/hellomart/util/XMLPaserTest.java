package com.hellomart.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class XMLPaserTest {
	
	private static final Logger logger = LoggerFactory.getLogger(XMLPaserTest.class);
	
	private XMLParser parser = new XMLParser("category.xml"); 
	
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void getChildrenTest() throws Exception {
		Vector<String> columns = parser.getChildren("냉장고");
		
		logger.debug(columns.toString());
	}
	
//	@Test
//	public void getAttributeValueTest() {
//		String table = parser.getAttributeValue("가전제품", "냉장고", "table");
//		String column = parser.getAttributeValue("냉장고", "소비전력", "column");
//		logger.debug("table : " + table);
//		logger.debug("column : " + column);
//	}
	
	@Test
	public void getValueTest() {
		String value = parser.getValue("냉장고", "소비전력");
		List<TableInformation> tableList = tableInfoXmlParser();
		for(TableInformation table : tableList){
			System.out.println(table.getTableEngName());
			System.out.println(table.getTableKorName());
			System.out.println(table.getColumnEngNameList());
			System.out.println(table.getColumnKorNameList());
			System.out.println(table.getColumnTypeList());
			System.out.println(table.getColumnUnitList());
			for(List<String> valueList : table.getColumnValueList()){
				for(String value1 : valueList){
					System.out.print(value1 + " ");
				}
				System.out.println();
			}
		}
		logger.debug(value);
	}
	
	private static List<TableInformation> tableInfoXmlParser() {
		XMLParser xmlParser = new XMLParser("category.xml");
		
		List<TableInformation> tableList = new ArrayList<TableInformation>();
		TableInformation tableInfo;
		List<String> mainCategorys = xmlParser.getChildren("카테고리"); 
		Map<String, List<String>> categoryMap = new HashMap<String, List<String>>();
		List<String> smallCategorys;
		for(int i = 0 ; i < mainCategorys.size() ; i++){
			smallCategorys = xmlParser.getChildren(mainCategorys.get(i));
			categoryMap.put(mainCategorys.get(i), smallCategorys);
		}
		
		StringBuffer sBuffer; Pattern p; Matcher m;
		
		StringTokenizer tokenizer;
		String valueList = null;
		String specValue = null;
		List<String> tokenResultValueList;
		
		for(int i = 0 ; i < mainCategorys.size() ; i++){
			smallCategorys = categoryMap.get(mainCategorys.get(i));
			for(int j = 0 ; j < smallCategorys.size() ; j++){
				tableInfo = new TableInformation();
				tableInfo.setColumnEngNameList(new ArrayList<String>());
				tableInfo.setColumnTypeList(new ArrayList<String>());
				tableInfo.setColumnUnitList(new ArrayList<String>());
				tableInfo.setColumnValueList(new ArrayList<List<String>>());
				tableInfo.setTableKorName(smallCategorys.get(j));
				tableInfo.setTableEngName(xmlParser.getAttributeValue(smallCategorys.get(j), "table"));
				tableInfo.setColumnKorNameList(xmlParser.getChildren(mainCategorys.get(i), smallCategorys.get(j)));
				for (String columnKorName : tableInfo.getColumnKorNameList()) {
					valueList = xmlParser.getValue(smallCategorys.get(j), columnKorName);

					tableInfo.getColumnEngNameList()
						.add(xmlParser.getAttributeValue(smallCategorys.get(j), columnKorName, "column"));
					
					String columnType = xmlParser.getAttributeValue(smallCategorys.get(j), columnKorName, "type");
					
					tableInfo.getColumnTypeList().add(columnType);
					
					if(columnType.equals("Integer") || columnType.equals("Double")){
						String[] value = valueList.split(",", 2);
						sBuffer = new StringBuffer();
						p = Pattern.compile("[^-?\\d+(,\\d+)*?\\.?\\d+?]+");
						m = p.matcher(value[0].trim());
						while (m.find()) {
							sBuffer.append(m.group());
						}
						String stringUnit = sBuffer.toString();
						if(stringUnit.indexOf("~") >= 0){
							String[] tempUnit = stringUnit.split("~");
							if(tempUnit.length == 0){
								tableInfo.getColumnUnitList().add(" ");
							}else{
								tableInfo.getColumnUnitList().add(tempUnit[1]);
							}
						}else{
							if(stringUnit.isEmpty()){
								tableInfo.getColumnUnitList().add(" ");
							}else{
								tableInfo.getColumnUnitList().add(stringUnit);
							}
						}
					}else{
						tokenResultValueList = new ArrayList<String>();
						tokenizer = new StringTokenizer(valueList.trim(), ",");
						while(tokenizer.hasMoreTokens()){ 
							specValue = tokenizer.nextToken();
							tokenResultValueList.add(specValue.trim());	
						}
						tableInfo.getColumnValueList().add(tokenResultValueList);
						tableInfo.getColumnUnitList().add(" ");
					}
				}
				tableList.add(tableInfo);
			}
		}
		return tableList;
	}
}
class TableInformation {
	private String tableEngName;
	private String tableKorName;
	private List<String> columnTypeList;
	private List<String> columnEngNameList;
	private List<String> columnKorNameList;
	private List<String> columnUnitList;
	private List<List<String>> columnValueList;

	public TableInformation() {}

	public String getTableEngName() {
		return tableEngName;
	}

	public void setTableEngName(String tableEngName) {
		this.tableEngName = tableEngName;
	}

	public String getTableKorName() {
		return tableKorName;
	}

	public void setTableKorName(String tableKorName) {
		this.tableKorName = tableKorName;
	}

	public List<String> getColumnTypeList() {
		return columnTypeList;
	}

	public void setColumnTypeList(List<String> columnTypeList) {
		this.columnTypeList = columnTypeList;
	}

	public List<String> getColumnEngNameList() {
		return columnEngNameList;
	}

	public void setColumnEngNameList(List<String> columnEngNameList) {
		this.columnEngNameList = columnEngNameList;
	}

	public List<String> getColumnKorNameList() {
		return columnKorNameList;
	}

	public void setColumnKorNameList(List<String> columnKorNameList) {
		this.columnKorNameList = columnKorNameList;
	}
	
	public List<String> getColumnUnitList() {
		return columnUnitList;
	}

	public void setColumnUnitList(List<String> columnUnitList) {
		this.columnUnitList = columnUnitList;
	}

	public List<List<String>> getColumnValueList() {
		return columnValueList;
	}

	public void setColumnValueList(List<List<String>> columnValueList) {
		this.columnValueList = columnValueList;
	}

	@Override
	public String toString() {
		return "TableInformation [tableEngName=" + tableEngName + ", tableKorName=" + tableKorName + ", columnTypeList="
				+ columnTypeList + ", columnEngNameList=" + columnEngNameList + ", columnKorNameList="
				+ columnKorNameList + ", columnUnitList=" + columnUnitList + ", columnValueList=" + columnValueList
				+ "]";
	}
	
}
