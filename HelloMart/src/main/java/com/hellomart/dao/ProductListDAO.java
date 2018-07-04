package com.hellomart.dao;

import java.util.Map;
import java.util.Vector;

import com.hellomart.dto.ProductList;

public interface ProductListDAO {
	
	Vector<ProductList> list(Map<String, Object> parametersMap);
	
	int getTotal(Map<String, String> categories);
	
	Vector<ProductList> listDetail(String sql);
	
}
