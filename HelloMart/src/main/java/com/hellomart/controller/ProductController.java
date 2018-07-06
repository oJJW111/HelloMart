package com.hellomart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/productView")
	public String productMainList(String no, String smallCategory, Model model){
		service.getDetailInfo(no, smallCategory, model);
		
		return "product/productView";
	}
}
