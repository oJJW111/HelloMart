package com.hellomart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dao.TodayViewDAO;
import com.hellomart.util.TodayViewUtils;

@Controller
public class TodayViewController {
	
	@Autowired
	private TodayViewDAO dao;
	
	@RequestMapping("/todayView")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		TodayViewUtils todayViewUtils = new TodayViewUtils(request, response);
		
		ModelAndView mav = new ModelAndView();
		
		String[] no = todayViewUtils.getAllValue();
		if(no.length == 0) { no = null; }
		
		mav.addObject("todayView", dao.list(no));
		
		mav.setViewName("mypage/todayView");
		return mav;
	}
}
