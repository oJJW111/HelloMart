package com.hellomart.service;

import java.util.List;

import com.hellomart.dto.Cart;

public interface CartService {

	List<Cart> listCart(String id);

	int sumMoney(String id);

	int countCart(int no, String id);

	void updateCart(Cart cart);

	void modifyCart(Cart cart);

}
