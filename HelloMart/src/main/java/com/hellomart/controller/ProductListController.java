package com.hellomart.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellomart.dto.ProductList;
import com.hellomart.service.ProductListService;

@Controller
@RequestMapping("/productList")
public class ProductListController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductListController.class);
	
	@Autowired
	ProductListService service;
	
	@RequestMapping("")
	public String productMainList(Model model, HttpServletRequest request,
			@RequestParam(value="mainCategory", required=false) String mainCategory,
			@RequestParam(value="smallCategory", required=false) String smallCategory,
			@RequestParam(value="page", required=false) Integer page){
		model.addAttribute("request", request);
		ProductList productList = new ProductList();
		productList.setComment(null);
		productList.setImagePath("/resources/images/product/refrigerator.jpg");
		productList.setMainCategory("가전제품");
		productList.setMfCompany("LG전자");
		productList.setNo(1);
		productList.setOrderCount(0);
		productList.setPrice(600000);
//		productList.setProdDate(null);
		productList.setProductName("냉장고팝니다.1");
		productList.setRegisterDate(null);
		productList.setRegisterID("papayaza222");
		productList.setScore(0);
		productList.setSmallCategory("냉장고");
		productList.setWeight(30);
		ProductList productList2 = new ProductList();
		productList2.setComment(null);
		productList2.setImagePath("/resources/images/product/refrigerator.jpg");
		productList2.setMainCategory("가전제품");
		productList2.setMfCompany("LG전자");
		productList2.setNo(2);
		productList2.setOrderCount(0);
		productList2.setPrice(600000);
//		productList2.setProdDate(null);
		productList2.setProductName("냉장고팝니다.2");
		productList2.setRegisterDate(null);
		productList2.setRegisterID("papayaza222");
		productList2.setScore(0);
		productList2.setSmallCategory("냉장고");
		productList2.setWeight(30);
		ArrayList<ProductList> list = new ArrayList<>();
		list.add(productList);
		list.add(productList2);
		model.addAttribute("list", list);
		Map<String, Object> attributes = service.list(model);

		model.addAllAttributes(attributes);
		
		return "product/productList";
	}
}