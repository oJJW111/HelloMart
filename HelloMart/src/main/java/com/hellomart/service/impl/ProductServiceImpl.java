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
		
<<<<<<< HEAD
		String smallCategoryEng 
			= xmlParser.getAttributeValue(dto.getSmallCategory(), "table");
=======
		XMLParser xmlParser = new XMLParser("category.xml");
		
		String smallCategoryEng = xmlParser.getAttributeValue(dto.getSmallCategory(), "table"); 
>>>>>>> refs/remotes/origin/jsb
		
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
		
<<<<<<< HEAD
		String sql = "select no, ";
		for(int i=0; i<columnListEng.size(); i++){
			sql += columnListEng.get(i);
		}		

		sql += " from productlist, " + smallCategoryEng
=======
		String sql = "select * from productlist, " + smallCategoryEng
>>>>>>> refs/remotes/origin/jsb
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
