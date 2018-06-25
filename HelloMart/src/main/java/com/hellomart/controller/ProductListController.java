package com.hellomart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.service.ProductListService;
import com.hellomart.service.impl.MainCategoryListServiceImpl;
import com.hellomart.service.impl.SmallCategoryListServiceImpl;

@Controller
public class ProductListController {
	ProductListService service;
	
	@RequestMapping("/productList")
	public String productList(HttpServletRequest request, Model model) {		
		if(request.getParameter("selectedSmallCategory") == null){
			service = new MainCategoryListServiceImpl();
			model.addAttribute("request", request);
			service.getAllList(model);
		}
		else{
			service = new SmallCategoryListServiceImpl();
			model.addAttribute("request", request);
			service.getAllList(model);
		}
		
		return "product/productList";
	}	
}
