package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hellomart.dao.SellerDAO;
import com.hellomart.dto.ProductList;
import com.hellomart.service.SellerService;
import com.hellomart.util.Page;
import com.hellomart.util.Upload;
import com.hellomart.util.XMLParser;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	SellerDAO dao;
	
	@Resource(name = "bbsPage")
	private Page page;
	
	@Autowired
	private Upload upload;
	
	List<String> productPartSpecEngName;
	
	@Override
	public void getSellerProductList(int pageNum, Model model, 
				String id, String servletPath) {
		int totalCount = 0;
		int pageSize = 5;// 한페이지에 보여줄 글의 갯수
		int pageBlock = 10; //한 블럭당 보여줄 페이지 갯수
		HashMap<String, Object> paramMap;
		
		totalCount = dao.getSellerProductCount(id);
		page.paging(pageNum, totalCount, pageSize, pageBlock, servletPath);
		paramMap = new HashMap<String, Object>();
		paramMap.put("startRow", page.getStartRow());
		paramMap.put("endRow", page.getEndRow());
		paramMap.put("id", id);
		
		ArrayList<ProductList> sellerProductList = dao.getSellerProductList(paramMap);
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
		model.addAttribute("id", id);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCode", page.getSb().toString());
		model.addAttribute("mapList", sellerProdReviewList);
	}

	@Override
	public void productPartSpec(Model model, Map<String, String> category) {
		String mainCategory = category.get("mainCategory");		
		String smallCategory = category.get("smallCategory");
		
		List<String> productPartSpecList;
		
		productPartSpecEngName = new ArrayList<String>();
		List<String> productPartSpecKorName = new ArrayList<String>();
		Map<String, List<String>> productPartSpecMap = new HashMap<String, List<String>>();
		
		StringTokenizer tokenizer;
		XMLParser xmlParser = new XMLParser("category.xml");
		String specValue = null;
		String specValueList = null;
		List<String> tokenResultValueList;
		
		try {
			
			productPartSpecList = xmlParser.getChildren(smallCategory);
			for(String productSpec : productPartSpecList){
				specValueList = xmlParser.getValue(smallCategory, productSpec); 
				System.out.println(smallCategory + "의 " + productSpec + "("
										+ xmlParser.getAttributeValue(productSpec, "column") + ")의 value : " + specValueList.trim());
				
				productPartSpecEngName.add(xmlParser.getAttributeValue(productSpec, "column"));
				productPartSpecKorName.add(productSpec);
				
				tokenResultValueList = new ArrayList<String>();
				tokenizer = new StringTokenizer(specValueList.trim(), ",");
				while(tokenizer.hasMoreTokens()){ 
					specValue = tokenizer.nextToken();
					tokenResultValueList.add(specValue);	
				}
				
				productPartSpecMap.put(productSpec, tokenResultValueList); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("mainCategory", mainCategory);
		model.addAttribute("smallCategory", smallCategory);
		model.addAttribute("specMapList", productPartSpecMap);
		model.addAttribute("specEngNameList", productPartSpecEngName);
		model.addAttribute("specKorNameList", productPartSpecKorName);
	}

	@Override
	public void sellerProductRegister(MultipartHttpServletRequest mRequest) {
		
		if(upload.fileUpload(mRequest)){
			System.out.println("성공");
		}else{
			System.out.println("실패");
		}
	}
}
