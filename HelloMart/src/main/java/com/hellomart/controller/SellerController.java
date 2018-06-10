package com.hellomart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public String seller() {
		return "seller/page";
	}
	
}
