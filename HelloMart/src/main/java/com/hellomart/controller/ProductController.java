package com.hellomart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellomart.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/productView")
	public String productMainList(Model model,
			@RequestParam("no") String no, 
			@RequestParam("smallCategory") String smallCategory){
		service.getDetailInfo(no, smallCategory, model);
		
		return "product/productView";
	}
}