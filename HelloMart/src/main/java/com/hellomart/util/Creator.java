package com.hellomart.util;

import java.util.Map;
import java.util.Set;

public class Creator {
	
	public static String createParameter(Map<String, String> map) {
		StringBuilder sb = new StringBuilder();
		Set<String> set = map.keySet();
		boolean first = true;
		
		for(String key : set) {
			String value = map.get(key);
			if(value != null) {
				if(first) {
					sb.append("?");
					first = false;
				}
				sb.append(key).append("=").append(value);
			}
		}
		
		return sb.toString();
	}
	
}
