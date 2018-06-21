package com.hellomart.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hellomart.dto.Account;

public class JoinFormValidator implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(JoinFormValidator.class);
	
	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		
		this.rejectIfEmptyOrWhitespace(errors, account);
		this.rejectIfNotMatch(errors, account);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}
	
	private void rejectIfEmptyOrWhitespace(Errors errors, Account account) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postCode", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "form.error.required");
		
		if(isNullOrEmpty(account.getRoadAddress())) {
			errors.rejectValue("roadAddress", "form.error.required");
		} else if(isNullOrEmpty(account.getDetailAddress())) {
			errors.rejectValue("detailAddress", "form.error.detailaddress.required");
		}
		
		if(isNullOrEmpty(account.getBirthYear()) ||
		   isNullOrEmpty(account.getBirthMonth()) ||
		   isNullOrEmpty(account.getBirthDay())) {
			errors.rejectValue("birthYear", "form.error.required");
		}
		
	}
	
	private void rejectIfNotMatch(Errors errors, Account account) {
		rejectIfNotMatch(errors, "form.error.email.notvalidate", 
				"email", account.getEmail(), "^[0-9a-zA-Z]+$");
		//@[0-9a-zA-Z]+\\.[a-zA-Z]{2,3}$
		if(!errors.hasFieldErrors("password")) {
			if(!account.getPassword().equals(account.getRe_password())) {
				errors.rejectValue("re_password", "form.error.password.notequal");
			}
		}
	}
	
	private boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	private void rejectIfNotMatch(Errors errors, String errorCode, 
			String field, String value, String reg) {
		logger.debug(field + " : " + value);
		if(!errors.hasFieldErrors(field)) {
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(field);
			
			if(!matcher.matches()) {
				logger.debug(field + "[" + value + "]" + " not match");
				errors.rejectValue(field, errorCode);
			}
		}
	}
	
	
}
