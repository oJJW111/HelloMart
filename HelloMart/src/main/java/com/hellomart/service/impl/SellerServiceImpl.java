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
	public Map<String, Object> productPartSpec(Model model, Map<String, String> category) {
		String mainCategory = category.get("mainCategory");		
		String smallCategory = category.get("smallCategory");
		
		List<String> productPartSpecList;
		
		List<String> productPartSpecEngName = new ArrayList<String>();
		List<String> productPartSpecKorName = new ArrayList<String>();
		Map<String, List<String>> productPartSpecMap = new HashMap<String, List<String>>();
		List<String> productPartSpecColumnTypeList = new ArrayList<String>();
		
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
				productPartSpecColumnTypeList.add(xmlParser.getAttributeValue(productSpec, "type"));
				productPartSpecKorName.add(productSpec);
				
				tokenResultValueList = new ArrayList<String>();
				tokenizer = new StringTokenizer(specValueList.trim(), ",");
				while(tokenizer.hasMoreTokens()){ 
					specValue = tokenizer.nextToken();
					tokenResultValueList.add(specValue.trim());	
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
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("specEngNameList", productPartSpecEngName);
		map.put("specKorNameList", productPartSpecKorName);
		map.put("specMapList", productPartSpecMap);
		map.put("tableName", xmlParser.getAttributeValue(smallCategory, "table"));
		map.put("productPartSpecColumnTypeList",productPartSpecColumnTypeList);
		return map;
	}

	@Override
	public void sellerProductRegister(MultipartHttpServletRequest mRequest, ProductList productList,
				Map<String, Object> tempTableInfoMap) {
		Map<String, Object> fileResultMap = upload.fileUpload(mRequest);
		if((Boolean)fileResultMap.get("isUpload")){
			String imagePath = (String)fileResultMap.get("imagePath");
			productList.setImagePath(imagePath);
		}else{
			return;
		}
		List<String> productPartSpecColumnNameList = (List<String>)tempTableInfoMap.get("specEngNameList");
		List<String> productPartSpecColumnTypeList = (List<String>)tempTableInfoMap.get("productPartSpecColumnTypeList");
		List<String> productPartSpecColumnValueList = new ArrayList<String>();
		for(int i = 0 ; i < productPartSpecColumnNameList.size() ; i++){
			String columnValue = mRequest.getParameter(productPartSpecColumnNameList.get(i));
			String columnName = productPartSpecColumnNameList.get(i);
			String columnType = productPartSpecColumnTypeList.get(i);
			if(columnType.equals("Integer")){
				if(columnValue.indexOf("~") > 0){
					String[] columnValueList = columnValue.split("~");
					for(String i : columnValueList){
						
					}
					
				}
			}else if(productPartSpecColumnTypeList.get(i).equals("Double")){
				if(columnValue.indexOf("~") > 0){
					
				}
			}
			if(requestValue.indexOf("~") > 0){
				
			}else{
				String str3 = null;
		        for(int j = 0 ; j < requestValue.length(); j++)
		        {    
		            if(48 <= requestValue.charAt(j) && requestValue.charAt(j) <= 57)
		                str3 += requestValue.charAt(j);
		        }	
			}

		}
	}
}
