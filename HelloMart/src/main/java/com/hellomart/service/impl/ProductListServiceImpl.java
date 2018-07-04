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
