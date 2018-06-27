package com.hellomart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	@RequestMapping("")
	public String main() {
		return "mypage/menu";
	}
	
	@RequestMapping("/info")
	public void info() {
	}
	
	@RequestMapping("/shoppingcart")
	public void shoppingcart() {
	}
	
	@RequestMapping("/point")
	public void point() {
	}
	
	@RequestMapping("/history")
	public void history() {
	}
	
	@RequestMapping("/todayView")
	public void todayView() {
	}
	
}
