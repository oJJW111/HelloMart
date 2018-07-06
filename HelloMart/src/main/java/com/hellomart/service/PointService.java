package com.hellomart.service;

import javax.servlet.http.HttpServletRequest;

import com.hellomart.dto.OrderList;


public interface PointService {
	public void insertPoint(HttpServletRequest request, OrderList orderList);
	public void insertPointList(HttpServletRequest request);
}
