package com.hellomart.service.impl;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.ReViewDAO;
import com.hellomart.dto.ReView;
import com.hellomart.service.ReViewService;

@Service
public class ReViewServiceImpl implements ReViewService{
	
	private static final Logger logger = LoggerFactory.getLogger(ReViewService.class);
	
	@Autowired
	private ReViewDAO dao;
	
	public ReViewServiceImpl() {
		
	}

	@Override
	public Vector<com.hellomart.dto.ReView> listReView(int startRow, int pageSize) {
		return dao.listReView(startRow, pageSize);
	}

	@Override
	public void reWrite(ReView reView) {
		dao.reWrite(reView);		
	}

	@Override
	public ReView ReView(int idx) {
		return dao.ReView(idx);
	}

	@Override
	public void deleteReView(int idx) {
		dao.deleteReView(idx);
	}

	@Override
	public void remodify(ReView reView) {
		dao.remodify(reView);
	}

	@Override
	public void Redelete(int idx) {
		dao.Redelete(idx);
	}
	
	@Override
	public int getReCount() {
		return dao.getReCount();
	}

	


}