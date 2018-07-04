package com.hellomart.dao;

import com.hellomart.dto.ProductList;

public interface ProductDAO {
	ProductList getProductInfo(String no);
}
