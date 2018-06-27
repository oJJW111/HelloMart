package com.hellomart.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView info(Principal principal) {
		ModelAndView mav = new ModelAndView();
		
		String id = principal.getName();
		Account account = service.getInfo(id);
		account.setId(id);
		
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "info");
		return mav;
	}
	
	
	@RequestMapping("/info/modify")
	public ModelAndView infoModify() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "modify");
		return mav;
	}
	
	@RequestMapping("/info/modifyPwd")
	public ModelAndView infoModifyPwd() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "modifyPwd");
		return mav;
	}
	
	@RequestMapping("/info/delete")
	public ModelAndView infoDelete() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "delete");
		return mav;
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
