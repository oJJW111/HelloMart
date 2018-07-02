package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.AccountDAO;
import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountDAO dao;
	
	public AccountServiceImpl() {
	}
	
	@Override
	public Account findAccount(String id) {
		return dao.findAccount(id);
	}
	
	@Override
	public void insertAccount(Account account) {
		dao.insertAccount(account);
	}
	
	@Override
	public Account getInfo(String id) {
		return dao.getInfo(id);
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
	public ArrayList<Account> accountList() {
		return dao.accountList();
	}

	@Override
	public void sellerApproval(List<String> idList) {
		for(String id : idList){
			dao.sellerApproval(id);
		}
	}

	@Override
	public void updateAccount(Account account) {
		dao.updateAccount(account);
	}

	@Override
	public boolean modifyPw(String pw,String new_pw,String id) {
		String oldPw = dao.getPasswd(id);
		if(!oldPw.equals(pw)){
			return false;
		}else{
			dao.modifyPw(new_pw,id);
			return true;
		}
		
	}

	@Override
	public String getPasswd(String id) {
		return dao.getPasswd(id);
	}

}