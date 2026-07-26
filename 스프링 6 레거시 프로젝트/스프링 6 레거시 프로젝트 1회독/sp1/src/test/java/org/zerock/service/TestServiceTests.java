package org.zerock.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class TestServiceTests {

	@Autowired
	TestService testService;

	@Test
	public void testAll() {
		log.info("is proxy?" + AopUtils.isAopProxy(testService));
		String str = "Second Test Second Test ";
//				+ "Second Test Second Test Second Test Second Test Second Test Second Test Second Test Second Test Second Test ";
		testService.insertAll(str);
	}
}
