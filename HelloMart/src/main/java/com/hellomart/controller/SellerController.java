package com.hellomart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.GET)
	public String sellerProductList(@PathVariable String pageNumString, 
								Model model) {
		int pageNum = Integer.parseInt(pageNumString);
		
		return "seller/page";
	}
}
