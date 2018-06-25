package com.hellomart.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.Account;
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
	public ModelAndView write(HttpSession session) {
		ModelAndView mav = new ModelAndView();
	
		String id = session.setAttribute("id", id);
		mav.addObject("id",req.getAttribute("id"));
		mav.setViewName("qaboard/QAWrite");
		
		return mav;
	}
	
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String writeProcess(QABoard qaboard){
		
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
		
	
	
	@RequestMapping(value = "/rewrite", method=RequestMethod.POST)
	public String rewriteProcess(QABoard qaboard) {
		service.reWrite(qaboard);
		return "redirect:/qaboard";
	}
	
	
	
}
