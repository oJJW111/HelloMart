package com.hellomart.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hellomart.dto.Account;
import com.hellomart.dto.ProductList;

@Component
public class ProductFormValidator implements Validator {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductFormValidator.class);
	
	@Override
	public void validate(Object target, Errors errors) {
		ProductList productList = (ProductList) target;
		
		this.rejectIfEmptyOrWhitespace(errors, productList);
		this.rejectIfNotMatch(errors, productList);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductList.class.isAssignableFrom(clazz); 
	}
	
	private void rejectIfEmptyOrWhitespace(Errors errors, ProductList productList) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mfCompany", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "form.error.required");

				
//		if(ValidationTools.isNullOrEmpty(productList.getProdYear()) ||
//		   ValidationTools.isNullOrEmpty(productList.getProdMonth()) ||
//		   ValidationTools.isNullOrEmpty(productList.getProdDay())) {
//			errors.rejectValue("prodYear", "form.error.required");
//		}
	}
	
	private void rejectIfNotMatch(Errors errors, ProductList productList) {
//		ValidationTools.rejectIfNotMatch(errors, "form.error.notvalidate.id", 
//				"id", account.getId(), "[a-zA-Z][0-9a-zA-Z]{5,19}");
//		ValidationTools.rejectIfNotMatch(errors, "form.error.notvalidate.password",
//				"password", account.getPassword(), "[a-zA-Z](?=.*\\d{3,})(?=.*\\W)[0-9a-zA-Z!@#$%^&*]{7,15}");
	}

}

