package com.hellomart.dao;

import java.util.HashMap;
import java.util.Vector;

import com.hellomart.dto.ProductList;

public interface ProductListDAO {
	
	// 상위 카테고리만 선택했을 때
	Vector<ProductList> listMain(String mainCategory, int offset, int limit);

	int getTotal();
	
	// 하위 카테고리를 선택했을 때
	Vector<ProductList> listSmall(String smallCategory, String mainCategory, int offset, int limit);

	// 상세조건을 입력하고 검색했을 때
	Vector<ProductList> listDetail(String sql);	
}
