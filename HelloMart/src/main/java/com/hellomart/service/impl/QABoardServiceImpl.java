package com.hellomart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.QABoardDAO;
import com.hellomart.dto.QABoard;
import com.hellomart.service.QABoardService;
import com.hellomart.util.PaginationResult;

@Service
public class QABoardServiceImpl implements QABoardService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QABoardService.class);
	
	@Autowired
	private QABoardDAO dao;
	
	public QABoardServiceImpl() {
		
	}

	@Override
	public PaginationResult<QABoard> list(int page) {
		return new PaginationResult<>(dao, page, 5, 5);
	}
	
	@Override
	public int getTotal() {
		return dao.getCount();
	}

	@Override
	public void insertQABoard(QABoard qaboard) {
		dao.insertQABoard(qaboard);
	}
	
	@Override 
	public QABoard viewQABoard(int idx) {
		return dao.viewQABoard(idx);
	}


	@Override
	public void viewCount(int idx) {
		dao.viewCount(idx);	
	}

	@Override
	public void modify(QABoard qaboard) {
		dao.modify(qaboard);
	}

	@Override
	public void delete(int idx) {
		dao.delete(idx);		
	}

}
