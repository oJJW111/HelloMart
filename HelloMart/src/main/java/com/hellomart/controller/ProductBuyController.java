package com.hellomart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellomart.dto.Account;
import com.hellomart.dto.OrderList;
import com.hellomart.service.AccountService;
import com.hellomart.service.ProductService;

@Controller
public class ProductBuyController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ProductService productService;

	@RequestMapping("/buy")
	public String buy(Model model, HttpServletRequest request){
		
		// 구매상품정보
		productService.getProductInfo(request.getParameter("no"), model); 
		// 상품구매수량
		model.addAttribute("orderCount", request.getParameter("orderCount"));
		// 구매자 정보
		Account account = accountService.get(request.getParameter("id"));
		model.addAttribute("account", account);
		
		return "product/productBuy";
	}
	
	@RequestMapping("/buyOk")
	public String buyOk(Model model, OrderList orderList){
		
		return "index";
	}
	
	@RequestMapping("/cartBuy")
	public String cartBuy(){
		return "product/productCartBuy";
	}
}
