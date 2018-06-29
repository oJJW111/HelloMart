package com.hellomart.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hellomart.service.SellerService;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.GET)
	public String sellerProductList(@PathVariable String pageNumString, 
								Model model, Principal principal) {
		int pageNum = Integer.parseInt(pageNumString);
		String id = principal.getName();
		ArrayList<HashMap<String,Object>> sellerProductList = sellerService.getSellerProductList(id);
		model.addAttribute("id", id);
		model.addAttribute("mapList", sellerProductList);
		return "seller/page";
	}
	
	@RequestMapping(value="/productRegister", method=RequestMethod.GET)
	public String sellerProductRegister(){
		return "seller/register";
	}
}
