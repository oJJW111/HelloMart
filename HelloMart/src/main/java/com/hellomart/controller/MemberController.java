package com.hellomart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/mypage")
public class MemberController {

	@RequestMapping(value="", method=RequestMethod.GET)
	public String member() {
		return "mypage/mypage";
	}
	
}
