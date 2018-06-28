package com.hellomart.service;

import java.util.List;

// 페이징서비스
public interface PageService<T> {
	int getTotal();
	List<T> getBoardList(int offset, int limit);
}
