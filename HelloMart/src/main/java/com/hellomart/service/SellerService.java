package com.hellomart.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface SellerService {
	public ArrayList<HashMap<String, Object>> getSellerProductList(String id);
}
