package com.hellomart.dao;

import java.util.List;

import com.hellomart.dto.OrderList;

public interface OrderDAO {
	public void insertOrder(OrderList orderList);
	public void insertOrderList(List<OrderList> orderLists);
}
