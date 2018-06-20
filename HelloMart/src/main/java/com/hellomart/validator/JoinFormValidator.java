package com.hellomart.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hellomart.dto.Account;

public class JoinFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	}
	
}
