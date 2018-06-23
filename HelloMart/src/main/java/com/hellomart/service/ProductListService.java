package com.hellomart.service;

import org.springframework.ui.Model;

public abstract class ProductListService {
	
	public ProductListService(){ }
	
    abstract public void getAllList(Model model);
	
}
