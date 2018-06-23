package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.hellomart.service.ProductListService;
import com.hellomart.util.XMLParser;

public class SmallCategoryListServiceImpl extends ProductListService{

	@Override
	public void getAllList(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		// 헤더에서 선택한 상위 카테고리 
		String mainCategory = request.getParameter("mainCategory"); 
		// 화면 중앙 선택창에서 좌측의 세부분류를 클릭했을 때, 어떤 카테고리를 골랐는지
		String selectedSmallCategory = request.getParameter("selectedSmallCategory");
		
		// 세부분류에 보여줄 선택한 상위 카테고리 밑의 카테고리들 
		List<String> smallCategory = new ArrayList<>();
		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(페이지에 보여줄 한글)
		List<String> columnList = new ArrayList<>();
		// 상세검색에서 보여줄 선택한 하위 카테고리의 컬럼 이름들(DB쿼리에 이용할 영어)
		List<String> columnListEng = new ArrayList<>(); 
		// 선택한 하위 카테고리의 컬럼 이름과 값을 매칭시켜둔 맵
		HashMap<String,	String> smallCategoryColumn = new HashMap<>();
		
		XMLParser xmlParser = new XMLParser("category.xml");
		
		try {
			smallCategory = xmlParser.getChildren(mainCategory); 
			columnList = xmlParser.getChildren(selectedSmallCategory);
			
			for(String column : columnList){
				String value = xmlParser.getValue(column); 
				columnListEng.add(xmlParser.getName(column));
				System.out.println(selectedSmallCategory + "의 " + column + "("
										+ xmlParser.getName(column) + ")의 value : " + value.trim());
					
					smallCategoryColumn.put(column, value); 
				}
			
		} catch (Exception e) {
			System.out.println("SmallCategoryListServiceImpl클래스 getAllList메소드 에러."); 
			e.printStackTrace();
		}
	
		model.addAttribute("mainCategory", mainCategory);
		model.addAttribute("smallCategory", smallCategory);
		model.addAttribute("columnList", columnList);
		model.addAttribute("columnListEng", columnListEng);
		model.addAttribute("smallCategoryColumn", smallCategoryColumn); 

		String selectedSmallCategoryEng = "refrigerator";
		// request.getParameter("selectedSmallCategoryEng")
		HashMap<String,	String> smallCategoryColumnEng = new HashMap<>(); 
		// (HashMap<String, String>)request.getAttribute("smallCategoryColumnEng")
		
		String sql = "select * from productlist where smallCategory = " + selectedSmallCategory
				+ "and productlist.no = " + selectedSmallCategoryEng + ".no ";
		
		// 상세검색눌렀을때
		if(request.getAttribute("smallCategoryColumnCount") != null){
			HashMap<String,	Integer> smallCategoryColumnCount 
							= (HashMap<String, Integer>)request.getAttribute("smallCategoryColumnCount");
			for(int i=0; i < columnListEng.size(); i++){
				for(int j=0; j < smallCategoryColumnCount.get(columnListEng.get(i)); j++){
					
				}
			}
			sql += "and ";
		}
		
		model.addAttribute("list", "list");		
	} 
}

