package com.hellomart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.util.XMLParser;
import com.hellomart.util.XMLPaserTest;

@Controller
public class ProductListController {
	private static final Logger logger = LoggerFactory.getLogger(ProductListController.class);
	@RequestMapping("/productList")
	public ModelAndView appliances() {
		ModelAndView mav = new ModelAndView();
		
		XMLParser xmlParser = new XMLParser("src/main/resources/category.xml");
		
		try {
			List<String> category = xmlParser.getChildren("에어컨");
			logger.debug(category.toString());
			
			for(String tagName : category) {
				String value = xmlParser.getValue(tagName);
				logger.debug(tagName + " : " + value);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mav.setViewName("product/productList");
		
		return mav;
	}
	
}
