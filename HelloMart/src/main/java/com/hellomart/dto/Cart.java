package com.hellomart.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Cart {
	private int no; // 상품번호
	private int Idx; // 장바구니 번호
	private String name; // 상품 이름
	private String id; // 사용자 id
	private String img; // 이미지
	private Timestamp date; // 날짜
	private int qty; // 수량
	private int price; // 가격
	
}
