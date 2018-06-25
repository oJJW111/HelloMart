package com.hellomart.service;

import java.util.Vector;

import com.hellomart.dto.QABoard;

public interface QABoardService {

	/* 게시글을 가져오는 메소드 */
	Vector<QABoard> listQABoard();
	
	
	/* 게시글을 삽입하는 메소드 */
	void insertQABoard(QABoard qaBoard);
	
	/* 게시글 카운트 증가 메소드 */
	void viewCount(int idx);
	
	/* 게시글 뷰화면 메소드 */
	QABoard viewQABoard(int idx);
	
	/* 게시글을 삭제하는 메소드 */
	void deleteQABoard(int idx);
	
	/* 답변글 작성 메소드 */
	void reWrite(QABoard qaboard);
	
}
