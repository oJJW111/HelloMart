package com.hellomart.service.impl;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.QABoardDAO;
import com.hellomart.dto.QABoard;
import com.hellomart.service.QABoardService;

@Service
public class QABoardServiceImpl implements QABoardService{
	
	private static final Logger logger = LoggerFactory.getLogger(QABoardService.class);
	
	@Autowired
	private QABoardDAO dao;
	
	public QABoardServiceImpl() {
		
	}

	@Override
	public Vector<QABoard> listQABoard() {
		return dao.listQABoard();
	}

	@Override
	public void insertQABoard(QABoard qaboard) {
		logger.debug(qaboard.toString());
		dao.insertQABoard(qaboard);
	}
	
	@Override
	public QABoard viewQABoard(int idx) {
		return dao.viewQABoard(idx);
	}

	@Override
	public void deleteQABoard(int idx) {
		dao.deleteQABoard(idx);
	}

	@Override
	public void viewCount(int idx) {
		dao.viewCount(idx);	
	}

	

	@Override
	public void reReLv(QABoard qaboard) {
		dao.reReLv(qaboard);		
	}

	@Override
	public void reWrite(QABoard qaboard) {
		dao.reWrite(qaboard);	
	}


	
	
	
	
	

}