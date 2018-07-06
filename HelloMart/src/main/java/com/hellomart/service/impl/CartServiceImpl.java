package com.hellomart.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.CartDAO;
import com.hellomart.dto.Cart;
import com.hellomart.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	private CartDAO dao;
	
	public CartServiceImpl(){
		
	}
	

	@Override	
	public List<Cart> listCart(String id) {
		return dao.listCart(id);
	}
	
	@Override
	public int sumMoney(String id){
		return dao.sumMoney(id);
	}
	
	@Override
	public int countCart(int no,String id){
		return dao.countCart(no, id);
	}
	
	@Override
	public void updateCart(Cart cart){
		dao.updateCart(cart);
	}
	
	@Override
	public void modifyCart(Cart cart){
		dao.modifyCart(cart);
	}


}
