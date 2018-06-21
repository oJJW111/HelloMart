package com.hellomart.controller;

import java.util.Vector;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.QABoard;
import com.hellomart.service.QABoardService;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
	
	@RequestMapping("/qawrite")
	public ModelAndView qaBoardWrite() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("qaboard/QAWrite");
		
		return mav;
	}
	
	@RequestMapping("/qaview")
	public ModelAndView qaBoardView() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("qaboard/QAView");
		
		return mav;
	}
	
}
