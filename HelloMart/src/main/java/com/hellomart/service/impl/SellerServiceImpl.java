package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.SellerDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	SellerDAO dao;
	
	@Override
	public ArrayList<HashMap<String, Object>> getSellerProductList(String id) {
		ArrayList<ProductList> sellerProductList = dao.getSellerProductList(id);
		HashMap<String, Object> sellerProductMap;
		ArrayList<HashMap<String,Object>> sellerProdReviewList
			= new ArrayList<HashMap<String,Object>>();
		for(ProductList i : sellerProductList){
			sellerProductMap = new HashMap<String, Object>();
			sellerProductMap.put("ImagePath", i.getImagePath());
			sellerProductMap.put("ProductName", i.getProductName());
			sellerProductMap.put("MfCompany", i.getMfCompany());
			sellerProductMap.put("MainCategory", i.getMainCategory());
			sellerProductMap.put("SmallCategory", i.getSmallCategory());
			sellerProductMap.put("Price", i.getPrice());
			sellerProductMap.put("Score", i.getScore());
			sellerProductMap.put("OrderCount", i.getOrderCount());
			sellerProductMap.put("count", dao.reviewCount(i.getNo()));
			sellerProdReviewList.add(sellerProductMap);
		}
		return sellerProdReviewList;
	}



	
	
	
}
