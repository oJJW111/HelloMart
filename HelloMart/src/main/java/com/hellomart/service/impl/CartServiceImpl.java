package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.CartDAO;
import com.hellomart.dao.ProductListDAO;
import com.hellomart.dto.Cart;
import com.hellomart.dto.ProductList;
import com.hellomart.service.CartService;
import com.hellomart.service.ProductListService;
import com.hellomart.util.Paging;
import com.hellomart.util.XMLParser;



@Service
public class CartServiceImpl implements CartService{
   
   @Inject
   SqlSession sqlsession;
   
   
   @SuppressWarnings("unused")
   private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
   
   @Autowired
   CartDAO dao;
   
   public CartServiceImpl() {
   }

   // 1. 장바구니 추가
   @Override
   public void insert(Cart cart) {
      dao.insert(cart);
   }

   // 2. 장바구니 목록
   @Override
   public List<Cart> listCart(String id) {
      return dao.listCart(id);
   }

   // 3. 장바구니 삭제
   @Override
   public void deleteCart(String id, int no) {
      dao.deleteCart(id, no);
   }

    // 4. 장바구니 수정
   @Override
   public void modifyCart(Cart cart) {
      dao.modifyCart(cart);
   }
   
   // 5. 장바구니 금액 합계
   @Override
   public int sumMoney(String id) {
      return dao.sumMoney(id);
   }
   
   // 6. 장바구니 동일한 상품 레코드 확인
   @Override
   public int countCart(int no, String id) {
      return dao.countCart(no, id);
   }

   // 7. 장바구니 상품 수량 변경
   @Override
   public void updateCart(Cart cart) {
      dao.updateCart(cart);
   }

   @Override
   public void deleteCartList(String id) {
	   dao.deleteCartList(id);
   }

}