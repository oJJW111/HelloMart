package com.hellomart.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hellomart.dto.OrderList;
import com.hellomart.dto.Point;


public interface PointService {
	public void insertPoint(HttpServletRequest request, OrderList orderList);
	public void insertPointList(HttpServletRequest request);
	public List<Point> getAllPointLog(String id);
	public List<Point> getPeriodPointLog(String id, Date startDate, Date endDate);
}
