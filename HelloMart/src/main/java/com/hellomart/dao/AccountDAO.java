package com.hellomart.dao;

import java.util.ArrayList;

import com.hellomart.dto.Account;

public interface AccountDAO {
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
	 * id와 일치하는 계정을 삭제한다.
	 * 
	 * @param id 삭제할 계정의 아이디
	 */
	void deleteAccount(String id);
	
	/**
	 * 계정 테이블의 모든 계정을 가져온다.
	 * 
	 * 
	 * @return 모든 계정 정보를 담고 있는 리스트
	 */
	ArrayList<Account> accountList();
	
	/**
	 * 판매진행중인 권한을 가진 아이디들을 모두 판매자권한으로 바꾼다.
	 * 
	 * @param id 바꿀 id
	 */
	void sellerApproval(String id);
	
	//테스트용
	/**
	 * 계정 테이블의 총 행의 수를 반환한다.
	 * 
	 * @return 계정 테이블의 총 행의 수
	 */
	int count();
	
	/**
	 * id와 일치하는 계정의 모든 정보를 가져온다. 테스트용으로 사용된다.
	 * 
	 * @param id 계정의 아이디
	 * @return 계정 정보를 담고 있는 Account 객체
	 */
	Account get(String id);
	
	/**
	 * 주문 처리 후, 유저의 보유 포인트 변경
	 * 
	 * @param id 계정의 아이디
	 * @param point 변동 포인트 양
	 * @param incDec 감소인지 증가인지 구분할 값
	 */
	void updatePoint(String id, int point, String incDec);
	
	/**
	 * 테이블을 초기화한다. 테스트용으로 사용된다.
	 */
	void truncate();
	//테스트용

}