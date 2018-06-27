package com.hellomart.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	private AccountService service;
	
	@RequestMapping("")
	public String main() {
		return "mypage/menu";
	}
	
	@RequestMapping("/info")
	public void info(Model model, Principal principal) {
		String id = principal.getName();
		Account account = service.getInfo(id);
		account.setId(id);
		model.addAttribute("account", account);
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
