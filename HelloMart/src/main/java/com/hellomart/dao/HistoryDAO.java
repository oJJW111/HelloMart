package com.hellomart.dao;

import java.util.HashMap;
import java.util.List;

import com.hellomart.dto.OrderList;

public interface HistoryDAO {
	 
	List<HashMap<String, String>> historylist(String id);
	
	int reviewCheck(String no,String id);

	List<OrderList> historyDatelist(String id, String startDate, String endDate);
}
