package com.hellomart.service;

import java.util.Vector;

import com.hellomart.dto.QABoard;

public interface QABoardService {

	/* 게시글을 가져오는 메소드 */
	Vector<QABoard> listQABoard();
	
	
	/* 게시글을 삽입하는 메소드 */
	void insertQABoard(QABoard qaBoard);
	
	/* 게시글을 삭제하는 메소드 */
	void deleteAccount(int idx);
	
}
