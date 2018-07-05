package com.hellomart.service;

import java.util.Map;
import org.springframework.ui.Model;

public interface ProductListService {
	Map<String, Object> list(Model model);
}
