package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.hellomart.service.ProductListService;
import com.hellomart.util.XMLParser;

public class MainCategoryListServiceImpl extends ProductListService{
 
	@Override
	public void getAllList(Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String mainCategory = request.getParameter("mainCategory");
		
		List<String> smallCategory = new ArrayList<>();
		
		XMLParser xmlParser = new XMLParser("category.xml");
		
		try {
			smallCategory = xmlParser.getChildren(mainCategory);
		} catch (Exception e) {
			System.out.println("MainCategoryListServiceImpl클래스 getAllList메소드 에러."); 
			e.printStackTrace();
		}
	
		model.addAttribute("mainCategory", mainCategory);
		model.addAttribute("smallCategory", smallCategory);

		String sql = "select * from productlist where mainCategory = " + mainCategory;
		model.addAttribute("list", "list");
	} 
}
