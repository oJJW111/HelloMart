package com.hellomart.service;

import java.util.Vector;

import com.hellomart.dto.ReView;

public interface ReViewService{

	
	/* 게시글을 가져오는 메소드 */
	Vector<ReView> listReView(int no,int startRow, int pageSize);
	
	/* 게시글을 삽입하는 메소드 */
	void reWrite(ReView reView);
	
	/* 게시글 뷰화면 메소드 */
	ReView ReView(int idx);
	
	/* 게시글을 삭제하는 메소드 */
	void deleteReView(int idx);

	/* 게시글의 전체 개수 불러오는 메소드 */
	int getReCount();
	
	/* 글 수정 하는 메소드 */
	void remodify(ReView reView);

	/* 글 삭제 하는 메소드 */
	void Redelete(int idx);
	
	
}
