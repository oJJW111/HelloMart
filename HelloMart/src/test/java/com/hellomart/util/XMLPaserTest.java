package com.hellomart.util;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class XMLPaserTest {
	
	private static final Logger logger = LoggerFactory.getLogger(XMLPaserTest.class);
	
	private XMLParser xmlParser = new XMLParser("src/main/resources/category.xml");
	
	@Test
	public void getChildrenTest() throws Exception {
		List<String> category = xmlParser.getChildren("에어컨");
		logger.debug(category.toString());
		
		for(String tagName : category) {
			String value = xmlParser.getValue(tagName);
			logger.debug(tagName + " : " + value);
		}
	}
	
}
