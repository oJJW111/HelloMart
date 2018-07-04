package com.hellomart.service;

import java.util.Map;

import org.springframework.ui.Model;

public interface ProductListService {

	// 상위 카테고리만 선택했을 때
	Map<String, Object> getMainList(String mainCategory, Integer page);

	// 하위 카테고리를 선택했을 때
	Map<String, Object> getSmallList(String mainCategory, String smallCategory, Integer page);

	// 상세조건을 입력하고 검색했을 때
	Map<String, Object> getDetailList(String mainCategory, String smallCategory, Integer page);	
}
