package com.hellomart.service;

import org.springframework.ui.Model;

public interface SellerService {
	public void getSellerProductList(int pageNum, Model model, 
				String id, String servletPath);
}
