package com.hellomart.service;

import java.util.Vector;

import com.hellomart.dto.QABoard;

public interface QABoardService {

   /* 게시글을 가져오는 메소드 */
   Vector<QABoard> listQABoard(int startRow, int pageSize);
   
   /* 게시글을 삽입하는 메소드 */
   void insertQABoard(QABoard qaboard);
   
   /* 게시글을 가져오는 메소드 */
   Vector<QABoard> listQABoardSub(String keyword, int startRow, int pageSize);
   
   /* 게시글을 가져오는 메소드 */
   Vector<QABoard> listQABoardCon(String keyword, int startRow, int pageSize);
   
   /* 게시글을 가져오는 메소드 */
   Vector<QABoard> listQABoardId(String keyword, int startRow, int pageSize);
   
   /* 게시글 카운트 증가 메소드 */
   void viewCount(int idx);
   
   /* 게시글의 전체 개수 불러오는 메소드 */
   int subjectCount(String keyword);
   
   /* 게시글의 전체 개수 불러오는 메소드 */
   int contentCount(String keyword);
   
   /* 게시글의 전체 개수 불러오는 메소드 */
   int idCount(String keyword);
   
   /* 게시글 뷰화면 메소드 */
   QABoard viewQABoard(int idx);
   
   /* 게시글의 전체 개수 불러오는 메소드 */
   int getCount();
   
   /* 글 수정 하는 메소드 */
   void modify(QABoard qaboard);

   /* 글 삭제 하는 메소드 */
   void delete(int idx);
   
   /* 코멘트 작성시 카운트 증가 */
   void cmtinc(int idx);
   
   /* 코멘트 삭제시 카운트 감소 */
   void cmtdec(int idx);
   
}