package com.hellomart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/product")
public class ProductListController {
	
	@RequestMapping("/list")
	public String productList() {
		
		
		return "product/productList";
	}
	
	@RequestMapping("/appliances")
	public ModelAndView appliances() {
		ModelAndView mav = new ModelAndView();
		
		
		
		
		mav.setViewName("redirect:list");
		
		return mav;
	}
	
}
