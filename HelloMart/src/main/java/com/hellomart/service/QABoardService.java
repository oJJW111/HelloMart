package com.hellomart.service;

import java.util.Map;

import com.hellomart.dto.QABoard;

public interface QABoardService {

	Map<String, Object> list(Integer page);
	
	/* 게시글을 삽입하는 메소드 */
	void insertQABoard(QABoard qaboard);
	
	/* 게시글 카운트 증가 메소드 */
	void viewCount(int idx);
	
	/* 게시글 뷰화면 메소드 */
	QABoard viewQABoard(int idx);
	
	/* 글 수정 하는 메소드 */
	void modify(QABoard qaboard);

	/* 글 삭제 하는 메소드 */
	void delete(int idx);
	
}
