package com.hellomart.dao;

import org.springframework.ui.Model;

import com.hellomart.dto.ProductList;

public interface ProductDAO {
	ProductList getProductInfo(String no);
}
