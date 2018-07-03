package com.hellomart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.hellomart.dto.ProductList;

public interface ProductListDAO extends Paging<ProductList> {
	
	// 상위 카테고리만 선택했을 때
	Vector<ProductList> list(int offset, int limit);

	int getTotal();
	
	// 하위 카테고리를 선택했을 때
	List<ProductList> getSmallList(String smallCategory);

	// 상세조건을 입력하고 검색했을 때
	List<ProductList> getDetailList(HashMap<String, String> map);	
}
