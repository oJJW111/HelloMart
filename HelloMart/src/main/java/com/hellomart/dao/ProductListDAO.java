package com.hellomart.dao;

import java.util.Map;
import java.util.Vector;

import com.hellomart.dto.ProductList;

public interface ProductListDAO {
	int getTotal(Map<String, Object> paramMap);
	Vector<ProductList> list(Map<String, Object> paramMap);
	void updateOrderCount(int no);
	
	/* 상품 리스트 테이블의 리뷰 갯수를 증가시키는 메소드 */
	void updatereviewCount(int no);
	void total(int star, int no);
}