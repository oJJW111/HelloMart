package com.hellomart.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		mav.addObject("count", service.getCount());
		mav.setViewName("qaboard/QABoardList");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
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
	
	@RequestMapping(value = "/modify", method=RequestMethod.GET)
	public ModelAndView modify(int idx) {
		
		ModelAndView mav = new ModelAndView();
		QABoard qaboard = service.viewQABoard(idx);
		mav.addObject("qaboard", qaboard);
		mav.setViewName("qaboard/modify");
		return mav;
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String modifyProcess(QABoard qaboard) {
		service.modify(qaboard);
		return "redirect:/qaboard";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String deleteProcess(int idx) {
		service.delete(idx);
		return "redirect:/qaboard";
	}
	

	
	
	
}
