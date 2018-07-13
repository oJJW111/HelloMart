package com.hellomart.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ProductListService {
	Map<String, Object> list(Model model);
	void updateOrderCount(HttpServletRequest request);
	void updateOrderCountList(HttpServletRequest request);
	

	/* 상품 리스트 테이블의 리뷰 갯수를 증가시키는 메소드 */
	void updatereviewCount(int no);
	void total(int star, int no);
}
