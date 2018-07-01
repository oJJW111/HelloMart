package com.hellomart.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.hellomart.dto.ProductList;

public interface SellerDAO {
	public ArrayList<ProductList> getSellerProductList(HashMap<String, Object> paramMap);
	public int reviewCount(int no);
	public int getSellerProductCount(String id);
}
