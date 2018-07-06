package com.hellomart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.Cart;
import com.hellomart.service.CartService;

public class CartController {
	
	@Autowired
	CartService cartservice;
	
	@RequestMapping("/cartlist")
	public ModelAndView list(HttpSession session,ModelAndView mav){
		String id = (String)session.getAttribute("id");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Cart> list = cartservice.listCart(id); // 장바구니 정보
		int sumMoney = cartservice.sumMoney(id); // 장바구니 전체 금액 호출
		// 장바구니 전체 긍액에 따라 배송비 구분
        // 배송료(10만원이상 => 무료, 미만 => 2500원)
		int fee = sumMoney >= 100000 ? 0 : 2500;
		map.put("list", list); // 장바구니 정보를 map에 저장
		map.put("count", list.size()); // 장바구니 상품의 유무
		map.put("sumMoney", sumMoney); // 장바구니 전체 금액
		map.put("fee", fee); // 배송금액
		map.put("allSum", sumMoney+fee); // 주문 상품 전체 금액
		mav.setViewName("order/cartlist"); // view(jsp)의 이름 저장
		mav.addObject("map", map); // map 변수 저장
				
		return mav;
		
	}
	
	@RequestMapping("/updatecart")
	public String update(@RequestParam int[] qty,@RequestParam int[] no,HttpSession session){
		String id = (String) session.getAttribute("id");
		
		for(int i=0;i<no.length;i++){
			Cart cart = new Cart();
			cart.setId(id);
			cart.setQty(qty[i]);
			cart.setNo(no[i]);
			cartservice.modifyCart(cart);
		}
		
		return "redirect:/";
	}

}	
