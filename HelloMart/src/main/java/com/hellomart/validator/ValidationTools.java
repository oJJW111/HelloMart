package com.hellomart.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;

public class ValidationTools {
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	public static void rejectIfNotMatch(Errors errors, String errorCode, 
			String field, String value, String reg) {
		rejectIfHasNotErrors(errors, errorCode, field, 
				() -> {
					Pattern pattern = Pattern.compile(reg);
					Matcher matcher = pattern.matcher(value);
					
					if(!matcher.matches()) {
						errors.rejectValue(field, errorCode);
					}
				});
	}
	
	public static void rejectIfNotEquals(Errors errors, String errorCode, 
			String field, String field2, String str1, String str2) {
		rejectIfHasNotErrors(errors, errorCode, field, 
				() -> {
					if(!str1.equals(str2)) {
						errors.rejectValue(field2, errorCode);
					}
				});
	}
	
	public static void rejectIfHasNotErrors(Errors errors, String errorCode, String field, MatcherStatement mstmt) {
		if(!errors.hasFieldErrors(field)) {
			mstmt.rejectIfNot();
		}
	}
	
}
