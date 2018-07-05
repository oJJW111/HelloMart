package com.hellomart.service;

import org.springframework.ui.Model;

public interface ProductService {

	void getDetailInfo(String no, String smallCategory, Model model);
 
}
