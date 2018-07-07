package com.hellomart.dao;

import java.util.HashMap;
import com.hellomart.dto.ProductList;

public interface ProductDAO {
	ProductList getProductInfo(String no);
	HashMap<String, String> getDetailInfo(HashMap<String, String> map); 
}