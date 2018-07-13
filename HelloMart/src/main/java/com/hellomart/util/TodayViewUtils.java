package com.hellomart.util;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodayViewUtils {

	public static final String KEYWORD = "TodayView";
	public static final int ONEDAY = 60*60*24;
	
	private CookieUtils cookieUtils;
	
	public TodayViewUtils(HttpServletRequest request, HttpServletResponse response) {
		cookieUtils = new CookieUtils(request, response);
	}
	
	public void addTodayView(String no) {
		cookieUtils.creatCookie(KEYWORD + no, no, ONEDAY);
	}
	
	public String[] getAllValue() {
		return cookieUtils.getAllValue();
	}
	
	public Vector<String> getAllValueWithKeyWord() {
		return cookieUtils.getAllValueWithKeyWord(KEYWORD);
	}
	
}
