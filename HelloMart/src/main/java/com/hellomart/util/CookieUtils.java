package com.hellomart.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CookieUtils(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void creatCookie(String key, String value, int period){
		Cookie c = new Cookie(key, value);
		c.setMaxAge(period);
		response.addCookie(c);
	}
	
	public String getValue(String name){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
	    	for(int i=0; i < cookies.length; i++){
	    		if(name.equals(cookies[i].getName())){
	    			return cookies[i].getValue();
	    		}
	        }
	    }
		return null;
	}
	
	public void removeAll(){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
	    	for(int i=0; i < cookies.length; i++){
	    		cookies[i].setMaxAge(0) ;
	    		response.addCookie(cookies[i]) ;
	        }
	    }
	}
	
	public void remove(String name){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
	    	for(int i=0; i < cookies.length; i++){
	    		if(name.equals(cookies[i].getName())){
	    			cookies[i].setMaxAge(0) ;
		    		response.addCookie(cookies[i]) ;
		    		break;
	    		}
	        }
	    }
	}
	
	public void append(String name, String value){
		
		
	}
	
	
}
