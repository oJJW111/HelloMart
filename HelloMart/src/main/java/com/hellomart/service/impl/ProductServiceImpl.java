package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.ProductDAO;
import com.hellomart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO dao;
	
	@Override
	public void getProductInfo(String no, Model model) {
		model.addAttribute("product", dao.getProductInfo(no)); 
		
		
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
//		String smallCategoryEng 
//			= ProductListServiceImpl.smallCategoryNameKorToEng.get(request.getParameter("smallCategory"));
		List<String> columnListEng = new ArrayList<>();
		
		String sql = "select no, ";
		for(int i=0; i<columnListEng.size(); i++){
			sql += columnListEng.get(i);
		}		
		HashMap<String, String> columnValue = new HashMap<>(); 
		model.addAttribute("detail", columnValue); 
	}
}
