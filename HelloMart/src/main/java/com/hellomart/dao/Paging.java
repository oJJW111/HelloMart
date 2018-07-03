package com.hellomart.dao;

import java.util.Vector;

public interface Paging<T> {
	Vector<T> list(int offset, int limit);
	int getTotal();
}
