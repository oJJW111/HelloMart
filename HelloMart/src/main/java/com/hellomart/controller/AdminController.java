package com.hellomart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public String admin(Model model) {
		ArrayList<Account> accountList = accountService.accountList();
		model.addAttribute("accountList", accountList);
		return "admin/page";
	}
	
	@RequestMapping(value="/deleteAccount", method=RequestMethod.POST)
	public String deleteAccount(@RequestParam(value="accountList", required=true) List<String> deleteAc){
		accountService.deleteAccount(deleteAc);
		return "redirect:/admin/page";
	}
	
	@RequestMapping(value="/sellerApproval", method=RequestMethod.POST)
	public String sellerApproval(@RequestParam(value="accountList", required=true) List<String> sellerAc){
		accountService.sellerApproval(sellerAc);
		return "redirect:/admin/page";
	}
	
	
}
