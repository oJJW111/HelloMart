package com.hellomart.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hellomart.service.SellerService;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.GET)
	public String sellerProductList(@PathVariable String pageNumString, 
								Model model, Principal principal,
								HttpServletRequest request) {
		int pageNum = Integer.parseInt(pageNumString);
		String servletPath = request.getServletPath();
		String id = principal.getName();
		sellerService.getSellerProductList(pageNum, model, id, servletPath);
		return "seller/page";
	}
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.POST)
	public String searchSellerProductList(@PathVariable String pageNumString, 
			Model model, Principal principal, HttpServletRequest request) {
		int pageNum = Integer.parseInt(pageNumString);
		String id = principal.getName();
		String servletPath = request.getServletPath();
		sellerService.getSellerProductList(pageNum, model, id, servletPath);
		return "seller/page";
	}
	
	@RequestMapping(value="/productRegister", method=RequestMethod.GET)
	public String sellerProductRegister(@RequestParam("mainCategoryInput") 
										String mainCategoryInput,
										@RequestParam("smallCategoryInput")
										String smallCategoryInput,
										Model model){
		Map<String, String> category = new HashMap<String, String>();
		category.put("mainCategory", mainCategoryInput);
		category.put("smallCategory", smallCategoryInput);
		sellerService.productPartSpec(model, category);
		return "seller/register";
	}
	
	@RequestMapping(value="/productRegister" ,method=RequestMethod.POST)
	public String sellerProductRegister(MultipartHttpServletRequest mRequest, Model model){
		
		sellerService.sellerProductRegister(mRequest);
		return "redirect:/seller/page/1";
	}
	
}
