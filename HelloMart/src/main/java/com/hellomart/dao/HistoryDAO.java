package com.hellomart.dao;

import java.util.List;

import com.hellomart.dto.OrderList;

public interface HistoryDAO {
	 
	List<OrderList> historylist(String id);
}
