package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hellomart.dao.SellerDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.SellerService;
import com.hellomart.util.Page;
import com.hellomart.util.Upload;
import com.hellomart.util.XMLParser;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	SellerDAO dao;
	
	@Resource(name = "bbsPage")
	private Page page;
	
	@Autowired
	private Upload upload;
	
	private List<TableInformation> tableList;
	
	public SellerServiceImpl() {
		tableList = tableInfoXmlParser();
	}
	
	@Override
	public void getSellerProductList(int pageNum, Model model, 
				String id, String servletPath) {
		int totalCount = 0;
		int pageSize = 5;// 한페이지에 보여줄 글의 갯수
		int pageBlock = 10; //한 블럭당 보여줄 페이지 갯수
		HashMap<String, Object> paramMap;
		
		totalCount = dao.getSellerProductCount(id);
		page.paging(pageNum, totalCount, pageSize, pageBlock, servletPath);
		paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", page.getStartRow());
		paramMap.put("endRow", page.getEndRow());
		paramMap.put("id", id);
		
		ArrayList<ProductList> sellerProductList = dao.getSellerProductList(paramMap);
		HashMap<String, Object> sellerProductMap;
		ArrayList<HashMap<String,Object>> sellerProdReviewList
			= new ArrayList<HashMap<String,Object>>();
		for(ProductList i : sellerProductList){
			sellerProductMap = new HashMap<String, Object>();
			sellerProductMap.put("ImagePath", i.getImagePath());
			sellerProductMap.put("ProductName", i.getProductName());
			sellerProductMap.put("MfCompany", i.getMfCompany());
			sellerProductMap.put("MainCategory", i.getMainCategory());
			sellerProductMap.put("SmallCategory", i.getSmallCategory());
			sellerProductMap.put("Price", i.getPrice());
			sellerProductMap.put("Score", i.getScore());
			sellerProductMap.put("OrderCount", i.getOrderCount());
			sellerProductMap.put("count", dao.reviewCount(i.getNo()));
			sellerProdReviewList.add(sellerProductMap);
		}
		model.addAttribute("id", id);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCode", page.getSb().toString());
		model.addAttribute("mapList", sellerProdReviewList);
	}

	@Override
	public void productPartSpec(Model model, String mainCategory, String smallCategory) {
		
		for(int i = 0 ; i < tableList.size() ; i++){
			if(tableList.get(i).){
				
			}
		}
		TableInformation tableInfo = 
		model.addAttribute("mainCategory", mainCategory);
		model.addAttribute("smallCategory", smallCategory);
		model.addAttribute("specList", tableInfo.getColumnValueList());
		model.addAttribute("specEngNameList", tableInfo.getColumnEngNameList());
		model.addAttribute("specKorNameList", tableInfo.getColumnKorNameList());
		model.addAttribute("specTypeList", tableInfo.getColumnTypeList());
	}
	
	

	@Override
	public Boolean PartProductValidCheck(MultipartHttpServletRequest mRequest, 
			Model model, String mainCategory, String smallCategory) {
		
		Boolean flag = true;
		TableInformation tableInfo = TableInformation.getInstance();
		if(mRequest.getFile("productImageFile").isEmpty()){
			model.addAttribute("msg", "파일이 업로드가 안 되었습니다.");
			flag = false;
		}else{
			String imageFileName = mRequest.getFile("productImageFile").getOriginalFilename();
			String extension = imageFileName.substring(imageFileName.lastIndexOf("."), imageFileName.length());
			if((!extension.toLowerCase().equals("gif")) 
				&& (!extension.toLowerCase().equals("png"))
				&& (!extension.toLowerCase().equals("jpg"))
				&& (!extension.toLowerCase().equals("jpeg"))){
				flag = false;
			}
			
			System.out.println(mRequest.getFile("productImageFile").getSize());
		}
		
		List<String> parameters = new ArrayList<String>();
		for(int i = 0 ; i < tableInfo.getColumnEngNameList().size() ; i++){
			String requestValue 
				= mRequest.getParameter(tableInfo.getColumnEngNameList().get(i));
			if(requestValue != ""){
				parameters.add(requestValue);
			}else{
				flag = false;
				model.addAttribute(
						tableInfo.getColumnEngNameList().get(i)
						+ "Msg", tableInfo.getColumnKorNameList().get(i) + "를 입력하시지 않았습니다.");
			}
		}
		if(!flag){
			productPartSpec(model, mainCategory, smallCategory);
		}
		return flag;
	}

	@Override
	public void sellerProductRegister(MultipartHttpServletRequest mRequest, ProductList productList) {
		Map<String, Object> fileResultMap = upload.fileUpload(mRequest);
		if((Boolean)fileResultMap.get("isUpload")){
			String imagePath = (String)fileResultMap.get("imagePath");
			productList.setImagePath(imagePath);
		}else{
			return;
		}
		TableInformation tableInfo = TableInformation.getInstance();
		Map<String, Object> productPartSpecColumnMap = new HashMap<String, Object>();
		
		StringBuffer sBuffer; Pattern p; Matcher m;
		int[] intNumbers = new int[2];
		double[] doubleNumbers = new double[2];
		
		for(int i = 0 ; i < tableInfo.getColumnEngNameList().size() ; i++){
			String columnValue = mRequest.getParameter(tableInfo.getColumnEngNameList().get(i));
			String columnName = tableInfo.getColumnEngNameList().get(i);
			String columnType = tableInfo.getColumnTypeList().get(i);
			if(columnType.equals("Integer")){
				int resultColumnValue;
				if(columnValue.indexOf("~") > 0){
					String[] columnValueList = columnValue.split("~");
					for(int j = 0 ; j < columnValueList.length ; j++){
						sBuffer = new StringBuffer();
						p = Pattern.compile("[-?0-9]+");
						m = p.matcher(columnValueList[j]);
						while (m.find()) {
							sBuffer.append(m.group());
						}
						intNumbers[j] = Integer.parseInt(sBuffer.toString());
					}
					resultColumnValue 
						= (int) (Math.random() * (intNumbers[1] - intNumbers[0] + 1)) + intNumbers[0]; 
				}else{
					sBuffer = new StringBuffer();
					p = Pattern.compile("[-?0-9]+");
					m = p.matcher(columnValue);
					while (m.find()) {
						sBuffer.append(m.group());
					}
					resultColumnValue = Integer.parseInt(sBuffer.toString());
				}
				productPartSpecColumnMap.put(columnName, resultColumnValue);
			}else if(columnType.equals("Double")){
				double resultColumnValue;
				if(columnValue.indexOf("~") > 0){
					String[] columnValueList = columnValue.split("~");
					for(int j = 0 ; j < columnValueList.length ; j++){
						sBuffer = new StringBuffer();
						p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");
						m = p.matcher(columnValueList[j]);
						while (m.find()) {
							sBuffer.append(m.group());
						}
						doubleNumbers[j] = Double.parseDouble(sBuffer.toString());
					}
					resultColumnValue 
						= (Math.random() * (doubleNumbers[1] - doubleNumbers[0])) + doubleNumbers[0];
					resultColumnValue 
						= Double.parseDouble(String.format("%.1f", resultColumnValue));
				}else{
					sBuffer = new StringBuffer();
					p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");
					m = p.matcher(columnValue);
					while (m.find()) {
						sBuffer.append(m.group());
					}
					resultColumnValue = Double.parseDouble(sBuffer.toString());
				}
				productPartSpecColumnMap.put(columnName, resultColumnValue);
			}else{
				productPartSpecColumnMap.put(columnName, columnValue);
			}
		}
		
		dao.insertProductInfo(productList);
		int no = dao.getNoProductList();
		
		String tableName = tableInfo.getTableName();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(tableName);
		sql.append(" (No, ");
		int i;
		for(i = 0 ; i < tableInfo.getColumnEngNameList().size() - 1;i++){
			sql.append(tableInfo.getColumnEngNameList().get(i) + ", ");
		}
		sql.append(tableInfo.getColumnEngNameList().get(i) + ") ");
		sql.append("VALUES(");
		sql.append(no + ", ");
		for(i = 0 ; i < tableInfo.getColumnTypeList().size() - 1 ; i++){
			if(tableInfo.getColumnTypeList().get(i).equals("Integer")){
				int result 
					= (int)productPartSpecColumnMap
					.get(tableInfo.getColumnEngNameList().get(i));
				sql.append(result + ", ");
			}else if(tableInfo.getColumnTypeList().get(i).equals("Double")){
				double result 
					= (double)productPartSpecColumnMap
					.get(tableInfo.getColumnEngNameList().get(i));
				sql.append(result + ", ");
			}else{
				String result 
					= (String)productPartSpecColumnMap
					.get(tableInfo.getColumnEngNameList().get(i));
				sql.append("'");
				sql.append(result);
				sql.append("', ");
			}
		}
		if(tableInfo.getColumnTypeList().get(i).equals("Integer")){
			int result 
				= (int)productPartSpecColumnMap
				.get(tableInfo.getColumnEngNameList().get(i));
			sql.append(result + ")");
		}else if(tableInfo.getColumnTypeList().get(i).equals("Double")){
			double result 
				= (double)productPartSpecColumnMap
				.get(tableInfo.getColumnEngNameList().get(i));
			sql.append(result + ")");
		}else{
			String result 
				= (String)productPartSpecColumnMap
				.get(tableInfo.getColumnEngNameList().get(i));
			sql.append("'");
			sql.append(result);
			sql.append("')");
		}
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("sql", sql.toString());
		dao.insertPartProductInfo(sqlMap);
	}
	
	private static List<TableInformation> tableInfoXmlParser() {
		XMLParser xmlParser = new XMLParser("category.xml");
		
		List<TableInformation> tableList = new ArrayList<TableInformation>();
		TableInformation tableInfo;
		String[] mainCategorys = new String[]{
			"가전제품", "IT", "모바일"
		};
		String[] categorys1 = new String[]{
			"냉장고","오븐_전자레인지","청소기", "에어컨", "세탁기", "공기청정기"
		};
		String[] categorys2 = new String[]{
			"노트북","데스크탑","모니터","프린터"
		};
		String[] categorys3 = new String[]{
			"스마트폰","태블릿"
		};
		Map<String, String[]> categoryMap = new HashMap<String, String[]>();
		categoryMap.put(mainCategorys[0], categorys1);
		categoryMap.put(mainCategorys[1], categorys2);
		categoryMap.put(mainCategorys[2], categorys3);
		
		StringTokenizer tokenizer;
		String valueList = null;
		String specValue = null;
		List<String> tokenResultValueList;
		
		for(int i = 0 ; i < mainCategorys.length ; i++){
			String[] smallCategorys = categoryMap.get(mainCategorys[i]);
			for(int j = 0 ; j < smallCategorys.length ; j++){
				tableInfo = new TableInformation();
				tableInfo.setColumnEngNameList(new ArrayList<String>());
				tableInfo.setColumnTypeList(new ArrayList<String>());
				tableInfo.setColumnValueList(new ArrayList<List<String>>());
				tableInfo.setTableKorName(smallCategorys[j]);
				tableInfo.setTableEngName(xmlParser.getAttributeValue(smallCategorys[j], "table"));
				tableInfo.setColumnKorNameList(xmlParser.getChildren(mainCategorys[i], smallCategorys[j]));
				for (String columnKorName : tableInfo.getColumnKorNameList()) {
					valueList = xmlParser.getValue(smallCategorys[j], columnKorName);

					tableInfo.getColumnEngNameList()
						.add(xmlParser.getAttributeValue(smallCategorys[j], columnKorName, "column"));
					tableInfo.getColumnTypeList()
						.add(xmlParser.getAttributeValue(smallCategorys[j], columnKorName, "type"));
					
					tokenResultValueList = new ArrayList<String>();
					tokenizer = new StringTokenizer(valueList.trim(), ",");
					while(tokenizer.hasMoreTokens()){ 
						specValue = tokenizer.nextToken();
						tokenResultValueList.add(specValue.trim());	
					}
					tableInfo.getColumnValueList().add(tokenResultValueList);
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
				+ columnKorNameList + ", columnValueList=" + columnValueList + "]";
	}

	
}