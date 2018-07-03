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
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductService;
import com.hellomart.util.XMLParser;
import com.mysql.cj.jdbc.PreparedStatement;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO dao;
	
	@Override
	public void getProductInfo(String no, Model model) {
		ProductList dto = dao.getProductInfo(no);
		
		model.addAttribute("product", dto); 
		
		String smallCategoryEng 
			= ProductListServiceImpl.smallCategoryNameKorToEng.get(dto.getSmallCategory());
		
		List<String> columnList = new ArrayList<>();
		List<String> columnListEng = new ArrayList<>();
		
		XMLParser xmlParser = new XMLParser("category.xml");

		try {
			columnList = xmlParser.getChildren(dto.getSmallCategory());
   
			for (String column : columnList) {
				columnListEng.add(xmlParser.getName(column));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "select * from productlist, " + smallCategoryEng
				+ " where productlist.no = " + no
				+ " and productlist.no = " + smallCategoryEng + ".no";
		
		HashMap<String, String> map = new HashMap<>();
		map.put("sql", sql);
		
		model.addAttribute("columnList", columnList);
		model.addAttribute("columnListEng", columnListEng);
		model.addAttribute("detail", dao.getDetailInfo(map)); 
		System.out.println(dao.getDetailInfo(map).keySet()); 
	}
}
