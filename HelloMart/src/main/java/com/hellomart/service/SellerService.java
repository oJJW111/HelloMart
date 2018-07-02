package com.hellomart.service;

import java.util.Map;

import org.springframework.ui.Model;

public interface SellerService {
	public void getSellerProductList(int pageNum, Model model, 
				String id, String servletPath);

	public void productPartSpec(Model model, Map<String, String> category);
}
