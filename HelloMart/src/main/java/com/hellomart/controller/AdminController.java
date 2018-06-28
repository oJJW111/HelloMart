package com.hellomart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellomart.service.AccountService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	AccountService accountService;
	
	private Map<String, Object> searchData;
		
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.GET)
	public String accountList(@PathVariable String pageNumString, 
								Model model) {
		int pageNum = Integer.parseInt(pageNumString);
		accountService.accountList(pageNum, model, searchData);
		return "admin/page";
	}
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.POST)
	public String searchAccountList(@PathVariable String pageNumString, 
										@RequestParam("id") String id, 
										@RequestParam("accountRole") String accountRole, 
										@RequestParam("sellerApply") String sellerApply, 
										Model model) {
		int pageNum = Integer.parseInt(pageNumString);
		searchData = new HashMap<String, Object>();
		searchData.put("id", id);
		searchData.put("accountRole", accountRole);
		searchData.put("sellerApply", sellerApply);
		accountService.accountList(pageNum, model, searchData);
		return "admin/page";
	}
	
	@RequestMapping(value="/deleteAccount", method=RequestMethod.POST)
	public String deleteAccount(@RequestParam(value="accountList", required=true) List<String> deleteAc){
		accountService.deleteAccountList(deleteAc);
		return "redirect:/admin/page/1";
	}
	
	@RequestMapping(value="/sellerApproval", method=RequestMethod.POST)
	public String sellerApproval(@RequestParam(value="accountList", required=true) List<String> sellerAc){
		accountService.sellerApproval(sellerAc);
		return "redirect:/admin/page/1";
	}
	
	
}