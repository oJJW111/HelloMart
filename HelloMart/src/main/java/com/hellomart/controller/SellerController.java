package com.hellomart.controller;

import java.security.Principal;
import java.util.HashMap;
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

import com.hellomart.dto.Account;
import com.hellomart.dto.ProductList;
import com.hellomart.service.SellerService;
import com.hellomart.validator.ProductFormValidator;

@Controller
@RequestMapping(value = "/seller")
public class SellerController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	ProductFormValidator productFormValidator;
	
	Map<String, String> category;
	
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
		sellerService.productPartSpec(model, category);
		model.addAttribute("ProductList", new ProductList());
		return "seller/register";
	}
	
	@RequestMapping(value="/productRegister" ,method=RequestMethod.POST)
	public String sellerProductRegister(MultipartHttpServletRequest mRequest, 
			@ModelAttribute("ProductList") @Valid ProductList productList,
				BindingResult bindingResult, Model model){
		String uri = null;
		if(bindingResult.hasErrors()) {
			Map<String, String> map = new HashMap<>();
			map.put("selectedYear", productList.getProdYear());
			map.put("selectedMonth", productList.getProdMonth());
			map.put("selectedDay", productList.getProdDay());
			
			model.addAttribute("prodDate", map);
			uri = "seller/register";
		}
		if(mRequest.getFile("productImageFile").isEmpty()){
			model.addAttribute("msg", "파일이 업로드가 안 되었습니다.");
			uri = "seller/register";
		}else{
			System.out.println(mRequest.getFile("productImageFile").getOriginalFilename());
			System.out.println(mRequest.getFile("productImageFile").getSize());
		}
		sellerService.sellerProductRegister(mRequest);
		uri = "redirect:/seller/page/1";
		
		return uri;
	}
	
}
