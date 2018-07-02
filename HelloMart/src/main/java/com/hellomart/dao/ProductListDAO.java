package com.hellomart.dao;

import java.util.List;

import org.springframework.ui.Model;

import com.hellomart.dto.ProductList;

public interface ProductListDAO {

	// 상위 카테고리만 선택했을 때
	public List<ProductList> getMainList(String mainCategory);

	// 하위 카테고리를 선택했을 때
	public List<ProductList> getSmallList(String smallCategory);

	// 상세조건을 입력하고 검색했을 때
	public List<ProductList> getDetailList();	
}
