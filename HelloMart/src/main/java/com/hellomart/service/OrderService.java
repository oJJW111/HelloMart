package com.hellomart.service;

import java.util.List;

import com.hellomart.dto.OrderList;

public interface OrderService {
	public void insertOrder(OrderList orderList);
	public void insertOrderList(List<OrderList> orderLists);
}
