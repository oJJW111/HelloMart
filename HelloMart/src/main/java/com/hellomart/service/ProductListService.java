package com.hellomart.service;

import org.springframework.ui.Model;

public interface ProductListService {

	// 상위 카테고리만 선택했을 때
	public void getMainList(String mainCategory, Model model);

	// 하위 카테고리를 선택했을 때
	public void getSmallList(String mainCategory, String smallCategory, Model model);

	// 상세조건을 입력하고 검색했을 때
	public void getDetailList(Model model);	
}
