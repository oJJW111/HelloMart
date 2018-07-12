package com.hellomart.dao;

import java.util.List;

import com.hellomart.dto.OrderList;
import com.hellomart.dto.Point;

public interface HistoryDAO {
	public List<OrderList> historyDatelist(String id, String startDate, String endDate);
	public List<OrderList> historylist(String id);
}
