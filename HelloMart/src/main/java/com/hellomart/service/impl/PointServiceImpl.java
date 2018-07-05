package com.hellomart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.PointDAO;
import com.hellomart.dto.Point;
import com.hellomart.service.PointService;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	PointDAO dao;

	@Override
	public void insertPoint(Point point) {
		dao.insertPoint(point); 
	}
	
}
