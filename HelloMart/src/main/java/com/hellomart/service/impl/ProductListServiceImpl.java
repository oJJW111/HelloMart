package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.ProductListDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;
import com.hellomart.util.PageHandling;
import com.hellomart.util.XMLParser;

@Service
public class ProductListServiceImpl implements ProductListService{
	static HashMap<String, String> smallCategoryNameKorToEng;
	
	@Autowired
	ProductListDAO dao;
	
	public ProductListServiceImpl() {
		smallCategoryNameKorToEng = new HashMap<String, String>();
		// category.xml의 태그 -> DB table명
		smallCategoryNameKorToEng.put("냉장고", "Refrigerator");
		smallCategoryNameKorToEng.put("오븐_전자레인지", "Microwave");
		smallCategoryNameKorToEng.put("청소기", "Cleaner");
		smallCategoryNameKorToEng.put("에어컨", "AirConditioner");
		smallCategoryNameKorToEng.put("세탁기", "Washer");
		smallCategoryNameKorToEng.put("공기청정기", "AirPurifier");
		smallCategoryNameKorToEng.put("노트북", "Notebook");
		smallCategoryNameKorToEng.put("데스크탑", "Desktop");
		smallCategoryNameKorToEng.put("모니터", "Monitor");
		smallCategoryNameKorToEng.put("프린터", "Printer");
		smallCategoryNameKorToEng.put("스마트폰", "Smartphone");
		smallCategoryNameKorToEng.put("태블릿", "Tablet");
	}

	// 상위 카테고리를 눌렀을 때
	// 해당 상위 카테고리의 하위 카테고리 목록과 상품 목록을 넘겨주는 메소드
	@Override
	public void getMainList(String mainCategory, Model model) {
		XMLParser xmlParser = new XMLParser("category.xml");
		
		// 세부분류에 보여줄 선택한 상위 카테고리 밑의 카테고리들
		List<String> smallCategoryList = new ArrayList<>();
		
		try {
			smallCategoryList = xmlParser.getChildren(mainCategory);
		} catch (Exception e) {
			System.out.println("ProductListServiceImpl클래스 getMainList메소드 에러."); 
			e.printStackTrace();
		}
		
		model.addAttribute("mainCategory", mainCategory);
		model.addAttribute("smallCategoryList", smallCategoryList);
		model.addAttribute("productList", dao.getMainList(mainCategory));
		
		pageSetting(dao.getMainList(mainCategory), model);
	}

	// 하위 카테고리를 눌렀을 때
	// 상세 검색 체크 박스로 보여줄 컬럼(=상세 검색 조건) 이름과 예시 값들을 넘겨주고
	// 선택한 하위 카테고리에 해당하는 상품 목록을 넘겨주는 메소드 
	@Override
	public void getSmallList(String mainCategory, String smallCategory, Model model) {
		getMainList(mainCategory, model);
		
		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(페이지에 보여줄 한글)
		List<String> columnList = new ArrayList<>();
		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(DB쿼리에 이용할 영어)
		List<String> columnListEng = new ArrayList<>();
		// 선택한 하위 카테고리의 컬럼 이름과 값을 매칭시켜둔 맵
		HashMap<String, String> smallCategoryColumn = new HashMap<>();

		XMLParser xmlParser = new XMLParser("category.xml");

		try {
			columnList = xmlParser.getChildren(smallCategory);

			for (String column : columnList) {
				String value = xmlParser.getValue(column);
				columnListEng.add(xmlParser.getName(column));
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
		
		pageSetting(dao.getSmallList(smallCategory), model);
	}
	

	@Override
	public void getDetailList(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		getSmallList(request.getParameter("mainCategory"), 
						request.getParameter("smallCategory"), model);
		
		String sql = createSQL(request); 
		HashMap<String, String> sqlMap = new HashMap<>();
		sqlMap.put("sql", sql);
		model.addAttribute("productList", dao.getDetailList(sqlMap));

		pageSetting(dao.getDetailList(sqlMap), model);
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
	
	public String createSQL(HttpServletRequest request){
		String smallCategory = request.getParameter("smallCategory");
		String smallCategoryEng = smallCategoryNameKorToEng.get(smallCategory);
		
		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(페이지에 보여줄 한글)
		List<String> columnList = new ArrayList<>();
		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(DB쿼리에 이용할 영어)
		List<String> columnListEng = new ArrayList<>();
		
		XMLParser xmlParser = new XMLParser("category.xml");

		try {
			columnList = xmlParser.getChildren(smallCategory);

			for (String column : columnList) {
				columnListEng.add(xmlParser.getName(column));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "select * from productlist natural join " + smallCategoryEng 
				+ " where productlist.smallCategory = '" + smallCategory + "'";
		System.out.println("sql 문장 : " + sql);
		
		// 상품 이름으로 검색
		if( (request.getParameter("search") != null) && !request.getParameter("search").equals("")){
			sql += " and productname = " + request.getParameter("search");
		}
		// 최저 가격 검색
		if(request.getParameter("price_range1") != null && !request.getParameter("price_range1").equals("")){
			sql += " and price >= " + request.getParameter("price_range1");
		}
		// 최고 가격 검색
		if(request.getParameter("price_range2") != null && !request.getParameter("price_range2").equals("")){
			sql += " and price <= " + request.getParameter("price_range2");
		}
			
		// 첫번째 추가조건이면 and로 처리하기 위해서, 구분하기 위한 변수 
		boolean isFirstAdd = true;
			
		for (int i = 0; i < columnListEng.size(); i++) {
			if (request.getParameterValues(columnListEng.get(i)) != null) {
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
							sql += " and " + columnListEng.get(i) + " = '" + value + "'";
							isFirstAdd = false;
						} else {
							sql += " or " + columnListEng.get(i) + " = '" + value + "'";
						}
						System.out.println("sql 문장 : " + sql);
					} else { // 범위 조건일 경우, 앞뒤 값으로 비교해서 검색
						if (isFirstAdd) {
							sql += " and (" + columnListEng.get(i) + " >= " + firstValue + " and "
									+ columnListEng.get(i) + " <= " + secondValue + ")";
							isFirstAdd = false;
						} else {
							sql += " or (" + columnListEng.get(i) + " >= " + firstValue + " and " + columnListEng.get(i)
									+ " <= " + secondValue + ")";
						}
						System.out.println("sql 문장 : " + sql);
					}
				}
			}
		}
		
		return sql;
	} // createSql 메소드 끝
	
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
