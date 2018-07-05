package com.hellomart.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.hellomart.dao.ProductListDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;
import com.hellomart.util.Paging;
import com.hellomart.util.XMLParser;

@Service
public class ProductListServiceImpl implements ProductListService{
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductListServiceImpl.class);
	
	@Autowired
	ProductListDAO dao;
	
	private final XMLParser xmlParser = new XMLParser("category.xml");
	
	public ProductListServiceImpl() {
	}

	private Map<String, Object> putDefault(
			Map<String, Object> modelMap,
			String mainCategory, String smallCategory,
			Integer page, String detailWhereSQL) {
		final int MAX_RESULT = 10;
		final int PAGE_PER_BLOCK = 10;
		
		page = page == null ? 1 : page;
		
		Map<String, String> categories = new HashMap<>();
		categories.put("mainCategory", mainCategory);
		categories.put("smallCategory", smallCategory);
		int total = dao.getTotal(categories);
		
		Paging paging = new Paging(total, page, MAX_RESULT, PAGE_PER_BLOCK);
		
		int offset = paging.getOffset();
		Vector<ProductList> list = null;
		
		Map<String, Object> parametersMap = new HashMap<>();
		parametersMap.put("mainCategory", mainCategory);
		parametersMap.put("smallCategory", smallCategory);
		parametersMap.put("offset", offset);
		parametersMap.put("limit", MAX_RESULT);
		parametersMap.put("detailWhereSQL", detailWhereSQL);
		if(offset != -1) {
			list = dao.list(parametersMap);
		}
		
		Vector<String> smallCategoryList = xmlParser.getChildren(mainCategory);
		
		modelMap.put("smallCategoryList", smallCategoryList);
		modelMap.put("paging", paging);
		modelMap.put("list", list);
		
		return modelMap;
	}
	
	private void putSmallCategories(Map<String, Object> modelMap, String mainCategory, String smallCategory) {
		Vector<String> columnList = xmlParser.getChildren(mainCategory, smallCategory);
		HashMap<String, String> smallCategoryColumn = new HashMap<>();
		Vector<String> columnListEng = new Vector<>();
		
		for (String column : columnList) {
			String value = xmlParser.getValue(smallCategory, column);
			smallCategoryColumn.put(column, value);
			columnListEng.add(xmlParser.getAttributeValue(smallCategory, column, "column"));
		}
		
		modelMap.put("columnList", columnList);
		modelMap.put("smallCategoryColumn", smallCategoryColumn);
		modelMap.put("columnListEng", columnListEng);
	}
	
	@Override
	public Map<String, Object> list(Model model) {
		Map<String, Object> requestMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest) requestMap.get("request");
		
		String mainCategory = null;
		String smallCategory = null;
		Integer page = null;
		
		Enumeration<String> parameterNames = request.getParameterNames();
		
		Map<String, String[]> paramMap = new HashMap<>();
		
		while(parameterNames.hasMoreElements()) {
			String param = parameterNames.nextElement();
			switch (param) {
			case "mainCategory" :
				mainCategory = request.getParameter(param);
				break;
			case "smallCategory" :
				smallCategory = request.getParameter(param);
				break;
			case "page" :
				page = Integer.parseInt(request.getParameter(param));
				break;
				default :
					String[] value = request.getParameterValues(param);
					paramMap.put(param, value);
			}
		}
		
		Map<String, Object> modelMap = new HashMap<>();
		String detailWhereSQL = null;
		
		if(smallCategory != null) {
			putSmallCategories(modelMap, mainCategory, smallCategory);
			if(!paramMap.isEmpty()) {
				String table = xmlParser.getAttributeValue(smallCategory, "table"); 
				detailWhereSQL = createDetailSQL(modelMap, paramMap, table);
			}
		}
		
		putDefault(modelMap, mainCategory, smallCategory, page, detailWhereSQL);
		
		return modelMap;
	}
	
	
	@SuppressWarnings("unchecked")
	public String createDetailSQL(Map<String, Object> modelMap, Map<String, String[]> paramMap, String table){
		Vector<String> columnList = (Vector<String>) modelMap.get("columnList");
		Vector<String> columnListEng = (Vector<String>) modelMap.get("columnListEng");
		Set<String> keySet = paramMap.keySet();
		
		for(String key : keySet) {
			for(String value : paramMap.get(key)) {
				logger.debug(value);
			}
		}
		
		String sql = "";
		
		if(paramMap.get("search")[0] != null && !paramMap.get("search")[0].isEmpty()){
			sql += " search ";
		}
		
		if(paramMap.get("price")[0] != null && !paramMap.get("price")[0].isEmpty()){
			sql += " and price >= " + paramMap.get("price")[0];
		}
		
		if(paramMap.get("price")[1] != null && !paramMap.get("price")[1].isEmpty()){
			sql += " and price <= " + paramMap.get("price")[1];
		}
		
		for (int i = 0; i < columnListEng.size(); i++) {
			if (request.getParameterValues(columnListEng.get(i)) != null) {
				
				// 같은 속성의 검색조건이 여러개면 or로 조건 처리
				boolean isFirstAdd = true;
				
				sql += " and(";
				
				String[] value = request.getParameterValues(columnListEng.get(i));
	
				for (int j = 0; j < value.length; j++) {
					StringTokenizer tokenizer = new StringTokenizer(value[j], "~");
					String firstValue = null;
					String secondValue = null;
	
					firstValue = findNumber(tokenizer.nextToken());
					while (tokenizer.hasMoreTokens()) {
						secondValue = findNumber(tokenizer.nextToken());
					}
	
					if (secondValue == null) { // 범위 조건이 아닐경우, 해당 값으로 검색
						if (isFirstAdd) {
							sql += " " + columnListEng.get(i) + " = '" + value[j].trim() + "'";
							isFirstAdd = false;
						} else {
							sql += " or " + columnListEng.get(i) + " = '" + value[j].trim() + "'";
						}
						System.out.println("sql 문장 : " + sql);
					} else { // 범위 조건일 경우, 앞뒤 값으로 비교해서 검색
						if (isFirstAdd) {
							sql += " (" + columnListEng.get(i) + " >= " + firstValue + " and "
									+ columnListEng.get(i) + " <= " + secondValue + ")";
							isFirstAdd = false;
						} else {
							sql += " or (" + columnListEng.get(i) + " >= " + firstValue + " and " + columnListEng.get(i)
									+ " <= " + secondValue + ")";
						}
						System.out.println("sql 문장 : " + sql);
					}
				} // for반복문 종료(같은 속성의 체크박스에 체크한 값들 반복)
				sql += ")";
				System.out.println("최종 sql 문장 : " + sql); 
			} // if (request.getParameterValues(columnListEng.get(i)) != null)
		}
		
		return sql;
	} // createSql 메소드 끝
	
	public String findNumber(String str){
		return str.replaceAll("[^0-9]", "");
	}
	
}
