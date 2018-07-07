package com.hellomart.controller;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.QABoard;
import com.hellomart.dto.ReView;
import com.hellomart.service.ReViewService;

@Controller
public class ReViewController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ReViewController.class);

	@Autowired
	private ReViewService service;

	@RequestMapping("/review")
	public ModelAndView reViewList(String pageNum) {
		ModelAndView mav = new ModelAndView();
		
		int pageSize = 5;
		int startPage = 0;
		int endPage=0;
		int pageBlock=0;
		int pageCount = service.getReCount();
		
		if(pageNum == null){
			pageNum ="1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize;
		
		Vector<ReView> list=null;
		
		if(pageCount>0){
			//10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
			list = service.listReView(startRow, pageSize);
			pageCount=pageCount/pageSize+(pageCount%pageSize==0?0:1);
			pageBlock=3;
			
			startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;
			
			endPage=startPage+pageBlock-1;
					
				if(endPage > pageCount){
					
					endPage = pageCount;
				}
		}
		mav.addObject("pageSize", pageSize);
		mav.addObject("pageBlock", pageBlock);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("pageCount", pageCount);
		mav.addObject("list",  list);
		mav.setViewName("review/ReViewList");
		
		return mav;
	}
	
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public ModelAndView view(int idx) {

		ModelAndView mav = new ModelAndView();
		ReView view = service.ReView(idx);
		mav.addObject("viewre", view);
		mav.setViewName("review/reView");

		return mav;
	}
	
	@RequestMapping(value = "/reWrite", method=RequestMethod.GET)
	public ModelAndView write(ReView review) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("review", review);		
		mav.setViewName("review/reWrite");
		return mav;
	}
	
	@RequestMapping(value = "/reWrite", method=RequestMethod.POST)
	public String writeProcess(ReView review){
		System.out.println("dtzsrszrz : " + review);
		service.reWrite(review);
		return "redirect:/review";
	}
	

	@RequestMapping(value = "/remodify", method = RequestMethod.GET)
	public ModelAndView remodify(int idx) {

		ModelAndView mav = new ModelAndView();
		ReView reView = service.ReView(idx);
		mav.addObject("reivew", reView);
		mav.setViewName("review/remodify");
		return mav;
	}

	@RequestMapping(value = "/remodify", method = RequestMethod.POST)
	public String remodifyProcess(ReView reView) {
		service.remodify(reView);
		return "redirect:/review";
	}

	@RequestMapping(value = "/redelete", method = RequestMethod.GET)
	public String redeleteProcess(int idx) {
		service.deleteReView(idx);
		return "redirect:/review";
	}

}
