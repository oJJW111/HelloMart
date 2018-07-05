package com.hellomart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.OrderDAO;
import com.hellomart.dto.OrderList;
import com.hellomart.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO dao;
	
	@Override
	public void insertOrder(OrderList orderList) {
		dao.insertOrder(orderList); 
	}

	@Override
	public void insertOrderList(List<OrderList> orderLists) {
		dao.insertOrderList(orderLists);
	}	
}
