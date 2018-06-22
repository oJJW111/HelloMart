package com.hellomart.service;

import java.util.ArrayList;
import java.util.List;

import com.hellomart.dto.Account;

public interface AccountService {
	/**
	 * <p>로그인 정보를 가져온다.
	 * 
	 * <ul>로그인 정보
	 * 	<li>PASSWORD
	 * 	<li>Authority
	 * </ul>
	 * 
	 * @return 로그인 정보를 담은 Account 객체
	 */
	Account findAccount(String id);
	
	/**
	 * 새로운 유저를 등록시킨다.
	 * 
	 * @param account 계정 정보를 담고있는 객체
	 */
	void insertAccount(Account account);
	
	/**
	 * id와 일치하는 계정들을 삭제한다.
	 * 
	 * @param id 삭제할 계정의 아이디
	 */
	void deleteAccount(List<String> idList);

	/**
	 * 계정 테이블의 총 행의 수를 반환한다.
	 * 
	 * @return 계정 테이블의 총 행의 수
	 */
	int count();
	
	/**
	 * 계정 테이블의 모든 계정 리스트를 반환한다.
	 * 
	 * @return 계정 테이블의 모든 계정 리스트
	 */
	ArrayList<Account> accountList();
	
	/**
	 * SELLER_READY 권한을 가지고 있는 계정들을 모두 SELLER 권한으로 바꾼다.
	 * 
	 * @param sellerAc 권한을 바꿀 id 리스트
	 */
	void sellerApproval(List<String> idList);
}
