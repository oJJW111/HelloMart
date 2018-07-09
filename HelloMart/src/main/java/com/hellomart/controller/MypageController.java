package com.hellomart.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;
import com.hellomart.validator.DeleteAccountValidator;
import com.hellomart.validator.JoinFormValidator;
import com.hellomart.validator.ModifyAccountInfoValidator;
import com.hellomart.validator.ModifyPasswordValidator;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired 
	private AccountService service;

	@Autowired
	private DeleteAccountValidator deleteAccountValidator;
	
	@Autowired
	private ModifyPasswordValidator modifyPasswordValidator;
	
	@RequestMapping("/menu")
	public String main() {
		return "mypage/menu";
	}
	
	@RequestMapping("/info")
	public ModelAndView info(Principal principal) {
		ModelAndView mav = new ModelAndView();
		
		String id = principal.getName();
		Account account = service.getInfo(id);
		
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "info");
		return mav;
	}
	
	
	@RequestMapping(value="/info/modify",method=RequestMethod.GET)
	public ModelAndView infoModify(Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		String id = principal.getName();
		Account account = service.getInfo(id);
		account.setId(id);
		
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "modify");
		return mav;
	}
	
	@RequestMapping(value="/info/modify",method=RequestMethod.POST)
	public ModelAndView Modify(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		new ModifyAccountInfoValidator().validate(account, bindingResult);
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("mypage/info/page");
			mav.addObject("viewPage", "modify");
			return mav;
		}
		service.updateAccount(account);
		mav.setViewName("redirect:/mypage/info");
		return mav;
	}
	
	@RequestMapping(value="/info/modifyPwd",method=RequestMethod.GET)
	public ModelAndView infoModifyPwd() {
		ModelAndView mav = new ModelAndView();
		Account account = new Account();
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "modifyPwd");
		return mav;
	}
	
	@RequestMapping(value="/info/modifyPwd",method=RequestMethod.POST)
	public ModelAndView ModifyPwd(@ModelAttribute("account") Account account,
						  			Principal principal, 
						  			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		String id = principal.getName();
		account.setId(id);
		String new_pw = account.getNew_password();
		
		modifyPasswordValidator.validate(account, bindingResult);
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("mypage/info/page");
			mav.addObject("viewPage", "modifyPwd");
			
			return mav;
		}
		
		service.modifyPw(id, new_pw);
		
		mav.setViewName("redirect:/mypage/info");
		return mav;
		
	}
	
	@RequestMapping(value="/info/delete",method=RequestMethod.GET)
	public ModelAndView infoDelete() {
		ModelAndView mav = new ModelAndView();
		Account account = new Account();
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "delete");
		return mav;
	}
	
	@RequestMapping(value="/info/delete", method=RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("account") Account account, Principal principal, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		String id = principal.getName();
		account.setId(id);
		
		deleteAccountValidator.validate(account, bindingResult);
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("mypage/info/page");
			mav.addObject("viewPage", "delete");
			return mav;
		}
		
		service.deleteAccount(id);
		
		mav.setViewName("redirect:/logout");
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
