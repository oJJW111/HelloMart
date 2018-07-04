package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.ProductDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductService;
import com.hellomart.util.XMLParser;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO dao;
	
	private XMLParser xmlParser = new XMLParser("category.xml");
	
	@Override
	public void getProductInfo(String no, Model model) {
		ProductList dto = dao.getProductInfo(no);
		
		model.addAttribute("product", dto); 
		
		String smallCategoryEng 
			= xmlParser.getAttributeValue(dto.getSmallCategory(), "table");
		
		List<String> columnList = new ArrayList<>();
		List<String> columnListEng = new ArrayList<>();
		
		try {
			columnList = xmlParser.getChildren(dto.getSmallCategory());
   
			for (String column : columnList) {
				columnListEng.add(xmlParser.getAttributeValue(column, "column"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "select no, ";
		for(int i=0; i<columnListEng.size(); i++){
			sql += columnListEng.get(i);
		}		

		sql += " from productlist, " + smallCategoryEng
				+ " where productlist.no = " + no
				+ " and produclist.no = " + smallCategoryEng + ".no";
		// dto = template.queryForObject(sql);
		HashMap<String, String> columnValue = new HashMap<>(); 
		for(int i=0; i<columnList.size(); i++){
			columnValue.put(columnList.get(i), "dto.get~~"); 
		}
		
		model.addAttribute("columnList", columnList);
		model.addAttribute("detail", columnValue); 
	}
}
