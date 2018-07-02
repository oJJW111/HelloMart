package com.hellomart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.service.ProductListService;

@Controller
@RequestMapping("/productList")
public class ProductListController {
	
	@Autowired
	ProductListService service;
	
	@RequestMapping("/main")
	public String productMainList(String mainCategory, Model model, HttpServletRequest request){
		model.addAttribute("request", request);
		
		service.getMainList(mainCategory, model);
		
		return "product/productList";
	}
	
	@RequestMapping("/small")
	public String productSmallList(String mainCategory, String smallCategory, Model model, HttpServletRequest request){
		model.addAttribute("request", request);
		
		service.getSmallList(mainCategory, smallCategory, model);
		
		return "product/productList";
	}
	
	@RequestMapping("/detail")
	public String productSmallDetailList(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		service.getDetailList(model); 
	
		return "product/productList";
	}
}
