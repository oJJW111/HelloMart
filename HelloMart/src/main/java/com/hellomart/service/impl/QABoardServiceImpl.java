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
   
   @SuppressWarnings("unused")
   private static final Logger logger = LoggerFactory.getLogger(QABoardService.class);
   
   @Autowired
   private QABoardDAO dao;
   

   @Override
   public Vector<QABoard> listQABoardSub(String keyword, int startRow, int pageSize) {
      return dao.listQABoardSub(keyword, startRow, pageSize);
   }

   @Override
   public Vector<QABoard> listQABoardCon(String keyword, int startRow, int pageSize) {
      return dao.listQABoardCon(keyword, startRow, pageSize);
   }

   @Override
   public Vector<QABoard> listQABoardId(String keyword, int startRow, int pageSize) {
      return dao.listQABoardId(keyword, startRow, pageSize);
   }

   @Override
   public Vector<QABoard> listQABoard(int startRow, int pageSize) {
      return dao.listQABoard(startRow, pageSize);
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
   public int getCount() {
      return dao.getCount();
   }
   
   @Override
   public int subjectCount(String keyword) {
      return dao.subjectCount(keyword);
   }

   @Override
   public int contentCount(String keyword) {
      return dao.contentCount(keyword);
   }

   @Override
   public int idCount(String keyword) {
      return dao.idCount(keyword);
   }

   public QABoardServiceImpl() {
      
   }

   @Override
   public void modify(QABoard qaboard) {
      dao.modify(qaboard);
   }

   @Override
   public void delete(int idx) {
      dao.delete(idx);      
   }

   @Override
   public void cmtinc(int idx) {
      dao.cmtinc(idx);
   }

   @Override
   public void cmtdec(int idx) {
      dao.cmtdec(idx);
   }
}