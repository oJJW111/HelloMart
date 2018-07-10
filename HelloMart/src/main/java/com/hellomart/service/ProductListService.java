package com.hellomart.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ProductListService {
	Map<String, Object> list(Model model);
	void updateOrderCount(HttpServletRequest request);
	void updateOrderCountList(HttpServletRequest request);
}