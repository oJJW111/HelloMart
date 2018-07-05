package com.hellomart.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ProductListService {

	// 상위 카테고리만 선택했을 때
	Map<String, Object> list(HttpServletRequest request, String mainCategory, String smallCategory, Integer page);

	// 상세조건을 입력하고 검색했을 때
	Map<String, Object> listDetail(String mainCategory, String smallCategory, Integer page);	
}
