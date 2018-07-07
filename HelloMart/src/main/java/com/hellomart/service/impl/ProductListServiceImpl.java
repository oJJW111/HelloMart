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
