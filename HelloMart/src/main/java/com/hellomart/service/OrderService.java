package com.hellomart.service;


import javax.servlet.http.HttpServletRequest;

import com.hellomart.dto.OrderList;

public interface OrderService {
	public void insertOrder(OrderList orderList);
	public void insertOrderList(HttpServletRequest request);
}
