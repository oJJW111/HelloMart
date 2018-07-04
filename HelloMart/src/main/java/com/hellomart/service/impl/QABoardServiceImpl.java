package com.hellomart.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.QABoardDAO;
import com.hellomart.dto.QABoard;
import com.hellomart.service.QABoardService;
import com.hellomart.util.Paging;

@Service
public class QABoardServiceImpl implements QABoardService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QABoardService.class);
	
	@Autowired
	private QABoardDAO dao;
	
	private int maxResult = 5;
	private int pagePerBlock = 5;
	
	public QABoardServiceImpl() {
		
	}

	@Override
	public Map<String, Object>  list(Integer page) {
		page = page == null ? 1 : page;
		
		int total = dao.getTotal();
		Paging paging = new Paging(total, page, maxResult, pagePerBlock);
		Vector<QABoard> list = dao.list(paging.getOffset(), maxResult);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("paging", paging);
		map.put("list", list);
		
		return map;
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
