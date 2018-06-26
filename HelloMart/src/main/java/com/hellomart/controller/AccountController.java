package com.hellomart.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;
import com.hellomart.validator.JoinFormValidator;

@Controller
public class AccountController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService service;
	 
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(new JoinFormValidator());
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.GET)
	public ModelAndView join() {
		return new ModelAndView("account/join", "account", new Account());
	}
	
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String joinProcess(Model model,
			@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, String> map = new HashMap<>();
			map.put("selectedYear", account.getBirthYear());
			map.put("selectedMonth", account.getBirthMonth());
			map.put("selectedDay", account.getBirthDay());
			
			model.addAttribute("birthdate", map);
			return "account/join";
		}
		
		service.insertAccount(account);
		return "redirect:/";
	}
	
}
