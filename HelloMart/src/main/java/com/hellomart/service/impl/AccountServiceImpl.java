package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.AccountDAO;
import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;
import com.hellomart.util.Page;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountDAO dao;
	
	@Resource(name = "bbsPage")
	private Page page;
	
	
	private ArrayList<Account> accountList;
	private HashMap<String, Object> paramMap;
	
	public AccountServiceImpl() {
	}
	
	@Override
	public Account findAccount(String id) {
		return dao.findAccount(id);
	}
	
	@Override
	public void insertAccount(Account account) {
		logger.debug(account.toString());
		dao.insertAccount(account);
	}

	@Override
	public void deleteAccount(String id) {
		dao.deleteAccount(id);
	}
	
	@Override
	public void deleteAccountList(List<String> idList) {
		for(String id : idList){
			dao.deleteAccount(id);
		}
	}

	@Override
	public int count() {
		return dao.count();
	}

	
	@Override
	public void accountList(int pageNum, Model model, Map<String, Object> searchData) {
		int totalCount = 0;
		int pageSize = 5;// 한페이지에 보여줄 글의 갯수
		int pageBlock = 10; //한 블럭당 보여줄 페이지 갯수
		paramMap = new HashMap<>();
		paramMap.put("flag", 0);
		if(searchData != null){
			paramMap.put("flag", 1);
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
				paramMap.put("sellerApply", sellerApply);
			}
		}
		
		totalCount = dao.accountCount(paramMap);
		page.paging(pageNum, totalCount, pageSize, pageBlock);
		paramMap.put("startRow", page.getStartRow());
		paramMap.put("endRow", page.getEndRow());
		accountList = dao.accountList(paramMap);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCode", page.getSb().toString());
		model.addAttribute("accountList", accountList);
	}

	@Override
	public void sellerApproval(List<String> idList) {
		for(String id : idList){
			dao.sellerApproval(id);
			dao.sellerProgressDelete(id);
		}
	}
}