<<<<<<< HEAD
package com.hellomart.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.ProductListDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;
import com.hellomart.util.Paging;
import com.hellomart.util.XMLParser;

@Service
public class ProductListServiceImpl implements ProductListService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductListServiceImpl.class);
	
	@Autowired
	ProductListDAO dao;
	
	private final XMLParser xmlParser = new XMLParser("category.xml");
	
	public ProductListServiceImpl() {
	}

	
	
	private Paging paging(String sql, Integer page) {
		page = page == null ? 1 : page;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sql", sql);
		int total = dao.getTotal(paramMap);
		
		return new Paging(total, page, 10, 10);
	}
	
	
	
	
	
	private Vector<ProductList> listBoard(String sql) {
		Vector<ProductList> list = null;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sql", sql);
		list = dao.list(paramMap);
		
		if(list.isEmpty()) {
			list = null;
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	private void smallCategoryDetails(Map<String, Object> modelMap, String mainCategory, String smallCategory) {
		Vector<String> columnList = xmlParser.getChildren(mainCategory, smallCategory);
		HashMap<String, String> smallCategoryColumn = new HashMap<>();
<<<<<<< HEAD
		Vector<String> columnListEng = new Vector<>();
=======

		XMLParser xmlParser = new XMLParser("category.xml");

		try {
			columnList = xmlParser.getChildren(smallCategory);

			for (String column : columnList) {
				String value = xmlParser.getValue(smallCategory, column);
				columnListEng.add(xmlParser.getAttributeValue(column, "column"));
				// System.out.println(smallCategory + "의 " + column + "("
				// + xmlParser.getName(column) + ")의 value : " + value.trim());

				smallCategoryColumn.put(column, value);
			}

		} catch (Exception e) {
			System.out.println("ProductListServiceImpl클래스 getSmallList메소드 에러.");
			e.printStackTrace();
		}

		model.addAttribute("smallCategory", smallCategory);
		model.addAttribute("smallCategoryColumn", smallCategoryColumn); 
		model.addAttribute("columnList", columnList);
		model.addAttribute("columnListEng", columnListEng);	 
		model.addAttribute("productList", dao.getSmallList(smallCategory)); 
>>>>>>> refs/remotes/origin/jsb
		
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
			case "mainCategory":
				mainCategory = request.getParameter(param);
				break;
			case "smallCategory":
				smallCategory = request.getParameter(param);
				break;
			case "page":
				page = Integer.parseInt(request.getParameter(param));
				break;
				default:
					String[] value = request.getParameterValues(param);
					paramMap.put(param, value);
			}
		}
		
<<<<<<< HEAD
=======
		return number;
	}
	
	public String createSQL(HttpServletRequest request){
		String smallCategory = request.getParameter("smallCategory");
		
		XMLParser xmlParser = new XMLParser("category.xml");

		String smallCategoryEng = xmlParser.getAttributeValue(smallCategory, "table"); 
>>>>>>> refs/remotes/origin/jsb
		
		
<<<<<<< HEAD
		/***** Model에 put할 맵 생성 *****/
		Map<String, Object> modelMap = new HashMap<>();
		/***** Model에 put할 맵 생성 *****/
		
		
		
		String table = null;
=======
>>>>>>> refs/remotes/origin/jsb
		try {
<<<<<<< HEAD
			table = xmlParser.getAttributeValue(mainCategory, smallCategory, "table");
		} catch (NullPointerException e) {}
		
		
		
		/***** 페이징처리 *****/
		String sql = "SELECT Count(*) FROM ProductList";
		String totalSql = createSQL(paramMap, sql, table, mainCategory, smallCategory, null, null);
		Paging paging = paging(totalSql, page);
		modelMap.put("paging", paging);
		/***** 페이징처리 *****/
		
		
		
		/***** small 카테고리 리스트 처리 *****/
		Vector<String> smallCategoryList = xmlParser.getChildren(mainCategory);
		modelMap.put("smallCategoryList", smallCategoryList);
		/***** small 카테고리 리스트 처리 *****/
		
		
		
		/***** 카테고리 세부 목록 처리 *****/
		if(smallCategory != null) {
			smallCategoryDetails(modelMap, mainCategory, smallCategory);
		}
		/***** 카테고리 세부 목록 처리 *****/
		
		
		
		/***** SQL 생성 *****/
		int offset = paging.getOffset();
		int limit = paging.getMaxResult();
		sql = "SELECT no, imagePath, productName, mfCompany, price, score, orderCount From ProductList";
		String listSql = createSQL(paramMap, sql, table, mainCategory, smallCategory, offset, limit);
		/***** SQL 생성 *****/
		
		
		
		/***** 상품 리스트 처리 *****/
		Vector<ProductList> list = listBoard(listSql);
		modelMap.put("list", list);
		/***** 상품 리스트 처리 *****/
		
		
		
		return modelMap;
	}
	
	
	public String createSQL(
			Map<String, String[]> paramMap, String select, String table, 
			String mainCategory, String smallCategory,
			Integer offset, Integer limit){
		StringBuilder sql = new StringBuilder(select);
		
		Set<String> columns = paramMap.keySet();
		
		if(!columns.isEmpty()) {
			sql
			.append(" ").append("NATURAL JOIN").append(" ")
			.append(table);
=======
			columnList = xmlParser.getChildren(smallCategory);

			for (String column : columnList) {
				columnListEng.add(xmlParser.getAttributeValue(column, "column"));
			}
		} catch (Exception e) {
			e.printStackTrace();
>>>>>>> refs/remotes/origin/jsb
		}
		
		sql
			.append(" ").append("WHERE").append(" ");
		
<<<<<<< HEAD
		sql
			.append("mainCategory").append(" = ").append("'").append(mainCategory).append("'");
		
		if(smallCategory != null) {
			sql.append(" and ").append("smallCategory").append(" = ").append("'").append(smallCategory).append("'");
=======
		// 상품 이름으로 검색
		if( (request.getParameter("search") != null) && !request.getParameter("search").equals("")){
			sql += " and productname = '" + request.getParameter("search") + "'";
>>>>>>> refs/remotes/origin/jsb
		}
<<<<<<< HEAD
		
		for(String column : columns) {
			boolean isFirst = true;
			StringBuilder sb = new StringBuilder();
				sb.append(" and (");
			for(String value : paramMap.get(column)) {
				switch (column) {
				case "search":
					sb.append("ProductName").append(" = ")
					.append("'%").append(value).append("%'");
					break;
				case "price1":
					sb.append("price").append(" >= ").append(value);
					break;
				case "price2":
					sb.append("price").append(" <= ").append(value);
					break;
					default:
						if(isFirst) {
							isFirst = false;
=======
		// 최저 가격 검색
		if(request.getParameter("price_range1") != null && !request.getParameter("price_range1").equals("")){
			sql += " and price >= " + request.getParameter("price_range1");
		}
		// 최고 가격 검색
		if(request.getParameter("price_range2") != null && !request.getParameter("price_range2").equals("")){
			sql += " and price <= " + request.getParameter("price_range2");
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
>>>>>>> refs/remotes/origin/jsb
						} else {
<<<<<<< HEAD
							sb.append(" or ");
=======
							sql += " or " + columnListEng.get(i) + " = '" + value[j].trim() + "'";
>>>>>>> refs/remotes/origin/jsb
						}
<<<<<<< HEAD
						
						if(value.indexOf('~') == -1) {
							sb
								.append(column)
								.append(" = ")
								.append("'").append(value).append("'");
=======
						System.out.println("sql 문장 : " + sql);
					} else { // 범위 조건일 경우, 앞뒤 값으로 비교해서 검색
						if (isFirstAdd) {
							sql += " (" + columnListEng.get(i) + " >= " + firstValue + " and "
									+ columnListEng.get(i) + " <= " + secondValue + ")";
							isFirstAdd = false;
>>>>>>> refs/remotes/origin/jsb
						} else {
							StringTokenizer tokenizer = new StringTokenizer(value, "~");
							
							sb
								.append("(")
									.append(column)
									.append(" >= ")
									.append(filterNumber(tokenizer.nextToken()))
									.append(" and ")
									.append(column)
									.append(" <= ")
									.append(filterNumber(tokenizer.nextToken()))
								.append(")");
						}
<<<<<<< HEAD
				}
			}
			sb.append(")");
			
			sql.append(sb.toString());
=======
						System.out.println("sql 문장 : " + sql);
					}
				} // for반복문 종료(같은 속성의 체크박스에 체크한 값들 반복)
				sql += ")";
				System.out.println("최종 sql 문장 : " + sql); 
			} // if (request.getParameterValues(columnListEng.get(i)) != null)
>>>>>>> refs/remotes/origin/jsb
		}
		
		if(offset != null && limit != null && offset != -1) {
			sql
				.append(" ").append("ORDER BY").append(" ")
				.append("no").append(" ")
				.append("DESC").append(" ")
				.append("LIMIT").append(" ").append(limit).append(" ")
				.append("OFFSET").append(" ").append(offset);
		}
		
		logger.debug("ProductLIst SQL : " + sql.toString());
		
		return sql.toString();
	}
	
	public String filterNumber(String str){
		return str.replaceAll("[^0-9]", "");
	}
	
}
=======
package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.ProductListDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;
import com.hellomart.util.PageHandling;
import com.hellomart.util.Paging;
import com.hellomart.util.XMLParser;

@Service
public class ProductListServiceImpl implements ProductListService{
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductListServiceImpl.class);
	
	@Autowired
	ProductListDAO dao;
	
	private XMLParser xmlParser = new XMLParser("category.xml");
	private int maxResult = 10;
	private int pagePerBlock = 10;
	
	public ProductListServiceImpl() {
	}

	// 상위 카테고리를 눌렀을 때
	// 해당 상위 카테고리의 하위 카테고리 목록과 상품 목록을 넘겨주는 메소드
	@Override
	public Map<String, Object> getMainList(String mainCategory, Integer page) {
		page = page == null ? 1 : page;
		
		int total = dao.getTotal();
		logger.debug("total : " + total);
		Paging paging = new Paging(total, page, maxResult, pagePerBlock);
		logger.debug("offset : " + paging.getOffset());
		Vector<ProductList> list = dao.listMain(mainCategory, paging.getOffset(), maxResult);
		
		Vector<String> smallCategoryList = xmlParser.getChildren(mainCategory);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("smallCategoryList", smallCategoryList);
		map.put("paging", paging);
		map.put("list", list);
		
		return map;
	}
	
	// 하위 카테고리를 눌렀을 때
	// 상세 검색 체크 박스로 보여줄 컬럼(=상세 검색 조건) 이름과 예시 값들을 넘겨주고
	// 선택한 하위 카테고리에 해당하는 상품 목록을 넘겨주는 메소드 
	@Override
	public Map<String, Object> getSmallList(String mainCategory, String smallCategory, Integer page) {
		page = page == null ? 1 : page;
		
		List<String> columnList = null;
		List<String> columnListEng = new ArrayList<>();
		HashMap<String, String> smallCategoryColumn = new HashMap<>();

		columnList = xmlParser.getChildren(smallCategory);
		
		for (String column : columnList) {
			String value = xmlParser.getValue(smallCategory, column);
			columnListEng.add(xmlParser.getAttributeValue(column, "column"));
			smallCategoryColumn.put(column, value);
		}
		
		int total = dao.getTotal();
		Paging paging = new Paging(total, page, maxResult, pagePerBlock);
		Vector<ProductList> list = dao.listSmall(mainCategory, smallCategory, paging.getOffset(), maxResult);
		
		Vector<String> smallCategoryList = xmlParser.getChildren(mainCategory);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("smallCategoryColumn", smallCategoryColumn);
		map.put("columnList", columnList);
		map.put("columnListEng", columnListEng);
		
		map.put("smallCategoryList", smallCategoryList);
		map.put("paging", paging);
		map.put("list", list);
		
		return map;
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
	
	public void pageSetting(List<ProductList> list, Model model){
		int numPerPage = 10;
		int pagePerBlock = 10;
		
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int nowPage = (request.getParameter("nowPage") != null) ?
				Integer.parseInt(request.getParameter("nowPage")) : 0;
				
		int nowBlock = (request.getParameter("nowBlock") != null) ?
						Integer.parseInt(request.getParameter("nowBlock")) : 0;
								
				
		PageHandling pageHandling =
				new PageHandling(list.size(), nowPage, nowBlock, numPerPage, pagePerBlock);
		
		pageHandling.setPageValue(model);
	}
	
}
>>>>>>> refs/remotes/origin/kms
