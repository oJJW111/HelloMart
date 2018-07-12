package com.hellomart.dao;

import java.util.HashMap;
import java.util.List;

import com.hellomart.dto.OrderList;
import com.hellomart.dto.ReView;

public interface HistoryDAO {
	 
	List<HashMap<String, String>> historylist(String id);
	
	ReView reviewCheck(String no,String id);

	List<OrderList> historyDatelist(String id, String startDate, String endDate);
}
