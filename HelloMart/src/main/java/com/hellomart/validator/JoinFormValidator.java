package com.hellomart.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hellomart.dto.Account;

public class JoinFormValidator implements Validator {

	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		
		this.rejectIfEmptyOrWhitespace(account, errors);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}
	
	private void rejectIfEmptyOrWhitespace(Account account, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "form.error.required");
		
		if(isNullOrEmpty(account.getPostCode())) {
			errors.rejectValue("postCode", "form.error.required");
		} 
		
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
	
	private boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
}
