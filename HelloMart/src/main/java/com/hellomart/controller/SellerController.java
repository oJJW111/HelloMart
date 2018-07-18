package com.hellomart.controller;

import java.security.Principal;

import javax.annotation.Resource;
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
		return "seller/sellerProductList";
	}
	
	
	@RequestMapping(value="/productRegister", method=RequestMethod.GET)
	public String sellerProductRegister(@RequestParam("mainCategoryInput") 
										String mainCategoryInput,
										@RequestParam("smallCategoryInput")
										String smallCategoryInput,
										Model model){
		sellerService.productPartSpec(model, mainCategoryInput, smallCategoryInput);
		model.addAttribute("ProductList", new ProductList());
		return "seller/productRegister";
	}
	
	@RequestMapping(value="/productRegister" ,method=RequestMethod.POST)
	public String sellerProductRegister(MultipartHttpServletRequest mRequest, 
			@ModelAttribute("ProductList") @Valid ProductList productList,
				BindingResult bindingResult, Principal principal, Model model){
		String uri = null;
		if(bindingResult.hasErrors()) {
			model.addAttribute("ProductList", new ProductList());
			sellerService.PartProductValidCheck(mRequest, model,
				productList.getMainCategory(), productList.getSmallCategory());
			return "seller/productRegister";
		}

		if(!sellerService.PartProductValidCheck(mRequest,model,
				productList.getMainCategory(), productList.getSmallCategory())){
			uri = "seller/productRegister";
		}else{
			productList.setRegisterID(principal.getName());
			sellerService.sellerProductRegister(mRequest, productList);
			uri = "redirect:/seller/page/1";
		}
		return uri;
	}
	
}