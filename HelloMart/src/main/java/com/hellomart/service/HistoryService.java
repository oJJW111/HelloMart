package com.hellomart.service;

import java.util.List;

import com.hellomart.dto.OrderList;

public interface HistoryService {
	public List<OrderList> historyDatelist(String id, String startDate, String endDate);
	public List<OrderList> historylist(String id);
}