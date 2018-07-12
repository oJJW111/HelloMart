package com.hellomart.dao;

import java.util.Map;
import java.util.Vector;

import com.hellomart.dto.ProductList;

public interface ProductListDAO {
	int getTotal(Map<String, Object> paramMap);
	Vector<ProductList> list(Map<String, Object> paramMap);
	void updateOrderCount(int no);
}