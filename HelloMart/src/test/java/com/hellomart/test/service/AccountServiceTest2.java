package com.hellomart.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hellomart.dao.AccountDAO;
import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class AccountServiceTest2 {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private AccountDAO dao;
		
	@Before
	public void before() {
		
	}
	
	@After
	public  void after() {
		
	}

	@Test
	public void methodTest() {
		int totalCount = 0;
		int pageSize = 5;// 한페이지에 보여줄 글의 갯수
		int pageBlock = 10; //한 블럭당 보여줄 페이지 갯수
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> searchData = new HashMap<>();
		searchData.put("id", "papayaza333");
		searchData.put("accountRole", "MEMBER");
		searchData.put("sellerApply", "SELLER_READY");
		if(searchData != null){
			String id = (String)searchData.get("id");
			String accountRole = (String)searchData.get("accountRole");
			String sellerApply = (String)searchData.get("sellerApply");
			if(!id.equals("")){
				paramMap.put("id", id);
			}
			if(!accountRole.equals("")){
				paramMap.put("accountRole", accountRole);
			}
			if(!sellerApply.equals("")){
				if(sellerApply.equals("없음")){
					sellerApply = null;
				}
				paramMap.put("sellerApply", sellerApply);
			}
		}
		
		totalCount = dao.count();
		
		paramMap.put("startRow", 1);
		paramMap.put("endRow", 5);
		ArrayList<Account> accountList = dao.accountList(paramMap);
		for(Account i : accountList){
			System.out.println(i.getId());
			System.out.println(i.getRole());
			System.out.println(i.getApply());
		}
	}
	
}
