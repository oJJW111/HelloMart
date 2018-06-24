package com.hellomart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

}
