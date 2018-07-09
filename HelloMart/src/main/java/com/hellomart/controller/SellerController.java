package com.hellomart.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hellomart.dto.ProductList;
import com.hellomart.service.SellerService;
import com.hellomart.validator.ProductFormValidator;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	ProductFormValidator productFormValidator;
	
	Map<String, String> category;
	Map<String, Object> tableInfoMap;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(productFormValidator);
	}
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.GET)
	public String sellerProductList(@PathVariable String pageNumString, 
								Model model, Principal principal,
								HttpServletRequest request) {
		int pageNum = Integer.parseInt(pageNumString);
		String servletPath = request.getServletPath();
		String id = principal.getName();
		sellerService.getSellerProductList(pageNum, model, id, servletPath);
		return "seller/page";
	}
	
	@RequestMapping(value="/page/{pageNumString}", method=RequestMethod.POST)
	public String searchSellerProductList(@PathVariable String pageNumString, 
			Model model, Principal principal, HttpServletRequest request) {
		int pageNum = Integer.parseInt(pageNumString);
		String id = principal.getName();
		String servletPath = request.getServletPath();
		sellerService.getSellerProductList(pageNum, model, id, servletPath);
		return "seller/page";
	}
	
	@RequestMapping(value="/productRegister", method=RequestMethod.GET)
	public String sellerProductRegister(@RequestParam("mainCategoryInput") 
										String mainCategoryInput,
										@RequestParam("smallCategoryInput")
										String smallCategoryInput,
										Model model){
		category = new HashMap<String, String>();
		category.put("mainCategory", mainCategoryInput);
		category.put("smallCategory", smallCategoryInput);
		tableInfoMap = sellerService.productPartSpec(model, category);
		model.addAttribute("ProductList", new ProductList());
		return "seller/register";
	}
	
	@RequestMapping(value="/productRegister" ,method=RequestMethod.POST)
	public String sellerProductRegister(MultipartHttpServletRequest mRequest, 
			@ModelAttribute("ProductList") @Valid ProductList productList,
				BindingResult bindingResult, Principal principal, Model model){
		boolean flag = false;
		String uri = null;
		Map<String, Object> tempTableInfoMap = tableInfoMap;
		if(bindingResult.hasErrors()) {
			Map<String, String> map = new HashMap<>();
			map.put("selectedYear", productList.getProdYear());
			map.put("selectedMonth", productList.getProdMonth());
			map.put("selectedDay", productList.getProdDay());
			
			model.addAttribute("prodDate", map);
			return "seller/register";
		}
		if(mRequest.getFile("productImageFile").isEmpty()){
			model.addAttribute("msg", "파일이 업로드가 안 되었습니다.");
			flag = true;
		}else{
			System.out.println(mRequest.getFile("productImageFile").getOriginalFilename());
			System.out.println(mRequest.getFile("productImageFile").getSize());
		}
		
		List<String> specEngNameList = (List<String>)tempTableInfoMap.get("specEngNameList");
		List<String> specKorNameList = (List<String>)tempTableInfoMap.get("specKorNameList");
		Map<String, List<String>> specMapList = (Map<String, List<String>>)tempTableInfoMap.get("specMapList");
		List<String> parameters = new ArrayList<String>();
		for(int i = 0 ; i < specEngNameList.size() ; i++){
			String requestValue = mRequest.getParameter(specEngNameList.get(i));
			System.out.println(requestValue);
			if(requestValue != ""){
				parameters.add(requestValue);
			}else{
				flag = true;
				model.addAttribute(specEngNameList.get(i) + "Msg", specKorNameList.get(i) + "를 입력하시지 않았습니다.");
			}
		}
		if(flag){
			model.addAttribute("mainCategory", category.get("mainCategory"));
			model.addAttribute("smallCategory", category.get("smallCategory"));
			model.addAttribute("specMapList", specMapList);
			model.addAttribute("specEngNameList", specEngNameList);
			model.addAttribute("specKorNameList", specKorNameList);
			uri = "seller/register";
		}else{
			productList.setRegisterID(principal.getName());
			sellerService.sellerProductRegister(mRequest, productList, tempTableInfoMap);
			uri = "redirect:/seller/page/1";
		}
		return uri;
	}
	
}