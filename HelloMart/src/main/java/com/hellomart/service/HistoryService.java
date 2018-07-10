package com.hellomart.service;

import java.util.List;

import com.hellomart.dto.OrderList;


public interface HistoryService {
	
	List<OrderList> historylist(String id);
}
