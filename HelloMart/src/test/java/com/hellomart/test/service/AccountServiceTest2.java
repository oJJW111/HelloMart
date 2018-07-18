package com.hellomart.test.service;

import javax.servlet.ServletContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hellomart.dao.AccountDAO;
import com.hellomart.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AccountServiceTest2 {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private AccountDAO dao;
	
	@Autowired ServletContext servletContext;
		
	@Before
	public void before() {
		
	}
	
	@After
	public  void after() {
		
	}

	@Test
	public void methodTest() {
		System.out.println(servletContext.getRealPath("/resources/"));
		
	}
	
}
