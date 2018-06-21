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
	public void insertQABoard(QABoard qaBoard) {
		dao.insertQABoard(qaBoard);
	}

	@Override
	public void deleteAccount(int idx) {
		dao.deleteAccount(idx);
	}
	
	
	

}
