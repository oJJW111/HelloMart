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
import com.hellomart.validator.JoinFormValidator;
import com.hellomart.validator.ModifyAccountInfoValidator;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	private AccountService service;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(new ModifyAccountInfoValidator());
	}
	
	@RequestMapping("")
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
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "modifyPwd");
		return mav;
	}
	
	@RequestMapping(value="/info/modifyPwd",method=RequestMethod.POST)
	public String ModifyPwd(@RequestParam("pw") String pw,
							@RequestParam("new_pw") String new_pw,
							@RequestParam("re_pw") String re_pw,
							Principal principal) {
		String id = principal.getName();
		boolean b = service.modifyPw(pw,new_pw,id);
		if(b){
			return "redirect:/mypage/info";
		}else{
			return "redirect:/mypage/info/modifyPwd";
		}
	}
	
	@RequestMapping(value="/info/delete",method=RequestMethod.GET)
	public ModelAndView infoDelete(String id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "delete");
		return mav;
	}
	
	@RequestMapping(value="/info/delete", method=RequestMethod.POST)
	public String delete(@RequestParam("pw") String pw, Principal principal) {
		String id = principal.getName();
		String oldpw = service.getPasswd(id);
		
		if(!oldpw.equals(pw)){
			return "redirect:/mypage/info/delete?fail=true";
		}
		service.deleteAccount(id);
		return "redirect:/logout";
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
