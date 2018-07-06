package com.hellomart.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hellomart.dto.ProductList;

public interface SellerService {
	public void getSellerProductList(int pageNum, Model model, 
				String id, String servletPath);

	public Map<String, Object> productPartSpec(Model model, Map<String, String> category);

	public void sellerProductRegister(MultipartHttpServletRequest mRequest,
				ProductList productList, Map<String, Object> tempTableInfoMap);
}
