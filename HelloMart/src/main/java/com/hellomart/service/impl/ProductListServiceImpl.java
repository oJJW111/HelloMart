package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.ProductListDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;
import com.hellomart.util.Paging;
import com.hellomart.util.XMLParser;

@Service
public class ProductListServiceImpl implements ProductListService{
	
	public static final int MAX_RESULT = 10;
	public static final int PAGE_PER_BLOCK = 10;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductListServiceImpl.class);
	
	@Autowired
	ProductListDAO dao;
	
	private XMLParser xmlParser = new XMLParser("category.xml");
	
	public ProductListServiceImpl() {
	}

	private Map<String, Object> putDefault(String mainCategory, String smallCategory, Integer page) {
		Map<String, Object> model = new HashMap<>(); 
		
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
		if(offset != -1) {
			list = dao.list(parametersMap);
		}
		
		Vector<String> smallCategoryList = xmlParser.getChildren(mainCategory);
		
		model.put("smallCategoryList", smallCategoryList);
		model.put("paging", paging);
		model.put("list", list);
		
		return model;
	}
	
	@Override
	public Map<String, Object> getMainList(String mainCategory, Integer page) {
		return putDefault(mainCategory, null, page);
	}
	
	@Override
	public Map<String, Object> getSmallList(String mainCategory, String smallCategory, Integer page) {
		Map<String, Object> model = putDefault(mainCategory, smallCategory, page);
		
		List<String> columnList = null;
		List<String> columnListEng = new ArrayList<>();
		HashMap<String, String> smallCategoryColumn = new HashMap<>();

		columnList = xmlParser.getChildren(smallCategory);
		
		for (String column : columnList) {
			String value = xmlParser.getValue(smallCategory, column);
			columnListEng.add(xmlParser.getAttributeValue(column, "column"));
			smallCategoryColumn.put(column, value);
		}
		
		model.put("smallCategoryColumn", smallCategoryColumn);
		model.put("columnList", columnList);
		model.put("columnListEng", columnListEng);
		
		return model;
	}
	
	
	
	@Override
	public Map<String, Object> getDetailList(String mainCategory, String smallCategory, Integer page) {
		
//		String sql = createSQL(request); 
//		HashMap<String, String> sqlMap = new HashMap<>();
//		sqlMap.put("sql", sql);
//		model.addAttribute("productList", dao.listDetail(sqlMap));

		Map<String, Object> map = new HashMap<>();
		
		return map;
	}
	
	public String findNumber(String str){
		String number = "";
		
		for(int i=0; i<str.length(); i++){
			// 아스키코드, 0 -> 48, 9 -> 57
			if( ((48 <= str.charAt(i)) && (str.charAt(i) <= 57)) || (str.charAt(i) == '.') ){
				number += str.charAt(i); 
			}
		}
		
		return number;
	}
	
//	public String createSQL(HttpServletRequest request){
//		String smallCategory = request.getParameter("smallCategory");
//		String smallCategoryEng =  xmlParser.getAttributeValue(smallCategory, "table");
//		
//		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(페이지에 보여줄 한글)
//		List<String> columnList = new ArrayList<>();
//		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(DB쿼리에 이용할 영어)
//		List<String> columnListEng = new ArrayList<>();
//		
//		XMLParser xmlParser = new XMLParser("category.xml");
//
//		try {
//			columnList = xmlParser.getChildren(smallCategory);
//
//			for (String column : columnList) {
//				columnListEng.add(xmlParser.getAttributeValue(column, "column"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		String sql = "select * from productlist natural join " + smallCategoryEng 
//				+ " where productlist.smallCategory = '" + smallCategory + "'";
//		
//		System.out.println("sql 문장 : " + sql);
//		
//		// 상품 이름으로 검색
//		if( (request.getParameter("search") != null) && !request.getParameter("search").equals("")){
//			sql += " and productname = " + request.getParameter("search");
//		}
//		
//		// 첫번째 추가조건이면 and로 처리하기 위해서, 구분하기 위한 변수 
//		boolean isFirstAdd = true;
//			
//		for (int i = 0; i < columnListEng.size(); i++) {
//			if (request.getParameterValues(columnListEng.get(i)) != null) {
//				String[] value = request.getParameterValues(columnListEng.get(i));
//
//				for (int j = 0; j < value.length; j++) {
//					StringTokenizer tokenizer = new StringTokenizer(value[j], "~");
//					String firstValue = null;
//					String secondValue = null;
//
//					firstValue = findNumber(tokenizer.nextToken());
//					while (tokenizer.hasMoreTokens()) {
//						secondValue = findNumber(tokenizer.nextToken());
//					}
//
//					if (secondValue == null) { // 범위 조건이 아닐경우, 해당 값으로 검색
//						if (isFirstAdd) {
//							sql += " and " + columnListEng.get(i) + " = '" + value + "'";
//							isFirstAdd = false;
//						} else {
//							sql += " or " + columnListEng.get(i) + " = '" + value + "'";
//						}
//						System.out.println("sql 문장 : " + sql);
//					} else { // 범위 조건일 경우, 앞뒤 값으로 비교해서 검색
//						if (isFirstAdd) {
//							sql += " and (" + columnListEng.get(i) + " >= " + firstValue + " and "
//									+ columnListEng.get(i) + " <= " + secondValue + ")";
//							isFirstAdd = false;
//						} else {
//							sql += " or (" + columnListEng.get(i) + " >= " + firstValue + " and " + columnListEng.get(i)
//									+ " <= " + secondValue + ")";
//						}
//						System.out.println("sql 문장 : " + sql);
//					}
//				}
//			}
//		}
//		
//		return sql;
//	} // createSql 메소드 끝
	
}