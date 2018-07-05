package com.hellomart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.dto.Account;
import com.hellomart.dto.OrderList;
import com.hellomart.dto.Point;
import com.hellomart.service.AccountService;
import com.hellomart.service.OrderService;
import com.hellomart.service.PointService;
import com.hellomart.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PointService pointService;

	@RequestMapping("/buy")
	public String buy(Model model, HttpServletRequest request){
		String no = request.getParameter("no");
		String smallCategory = request.getParameter("smallCategory");
		
		// 구매상품정보
		productService.getDetailInfo(no, smallCategory, model); 
		// 상품구매수량
		model.addAttribute("orderCount", request.getParameter("orderCount"));
		// 구매자 정보
		Account account = accountService.get(request.getParameter("id"));
		model.addAttribute("account", account);
		
		return "product/productBuy";
	}
	
	@RequestMapping("/buyOk")
	public String buyOk(OrderList orderList, HttpServletRequest request){
		orderService.insertOrder(orderList);
		
		Point point = new Point();
		
		String incDec = request.getParameter("incDec");
		
		int totalPrice = orderList.getOrderPrice();
		int grade = accountService.get(orderList.getOrderId()).getGrade();
		double qty = 0; 
		
		String content = request.getParameter("prodName"); 
		if(incDec.equals("+")){
			qty = totalPrice * (0.01 + grade);
			content += "의 구매로" + qty + "만큼 " +"증가";	
		}
		if(incDec.equals("-")){
			qty = Double.parseDouble(request.getParameter("point"));
			content += "의 구매에 " + qty + "만큼 사용해서 " + "감소";
		}
		
		point.setId(orderList.getOrderId());
		point.setIncDec(incDec); 
		point.setPoint((int)Math.ceil(qty));
		point.setContent(content);
		
		pointService.insertPoint(point);
		
		return "index";
		// return "마이페이지 구매리스트로?";
	}
	
	@RequestMapping("/cartBuy")
	public String cartBuy(){
		return "product/productCartBuy";
	}
	
	@RequestMapping("/cartBuyOk")
	public String cartBuyOk(List<OrderList> orderLists){
		orderService.insertOrderList(orderLists); 
		
		return "마이페이지로?";
	}
	
	@RequestMapping("/cartList")
	public String cartList(String id){
		return "카트 목록 보기 페이지로";
	}
}





