package com.hellomart.dao;

import java.util.ArrayList;

import com.hellomart.dto.ProductList;

public interface SellerDAO {
	public ArrayList<ProductList> getSellerProductList(String id);
	public int reviewCount(int no);
}
