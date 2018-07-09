package com.hellomart.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class TodayViewUtils {

	private CookieUtils cookieUtils;
	
	public TodayViewUtils(HttpServletRequest request, HttpServletResponse response) {
		cookieUtils = new CookieUtils(request, response);
	}
	
	public void addTodayView(String no) {
		cookieUtils.creatCookie(no, no, 60*60*24);
	}
	
	public String[] getAllValue() {
		return cookieUtils.getAllValue();
	}
	
}
