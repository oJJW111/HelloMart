package com.hellomart.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.QABoard;
import com.hellomart.service.QABoardService;

@Controller
public class QABoardController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QABoardController.class);
	
	@Autowired
	private QABoardService service;
	
	@RequestMapping("/qaboard")
	public ModelAndView qaBoardList() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list",  service.listQABoard());
		
		mav.setViewName("qaboard/QABoardList");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public ModelAndView write() {
		return new ModelAndView("qaboard/QAWrite", "qaboard", new QABoard());
	}
	
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String writeProcess(@ModelAttribute("qaboard") @Valid QABoard qaboard, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "qaboard/QAWrite";
		}
		
		service.insertQABoard(qaboard);
		return "redirect:/qaboard";
	}
	
	@RequestMapping(value = "/view", method=RequestMethod.GET)
	public ModelAndView view(int idx) {
		
		ModelAndView mav = new ModelAndView();
		service.viewCount(idx);
		QABoard view = service.viewQABoard(idx);
		mav.addObject("view",view);
		mav.setViewName("qaboard/QAView");
		
		return mav;
	}
	
	@RequestMapping(value = "/rewrite", method=RequestMethod.GET)
	public ModelAndView reWrite(int idx) {
		
		ModelAndView mav = new ModelAndView();
		QABoard qaboard = service.viewQABoard(idx);
		mav.addObject("qaboard", qaboard);
		mav.setViewName("qaboard/reWrite");
		return mav;
	}
		
	
	
	@RequestMapping(value = "/rewriteOk", method=RequestMethod.POST)
	public String rewriteOk(@ModelAttribute("qaboard") @Valid QABoard qaboard, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "qaboard/reWrite";
		}
		service.reReLv(qaboard);
		service.reWrite(qaboard);
		return "redirect:/qaboard";
	}
	
	
	
}
