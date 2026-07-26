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
	log.info("is proxy? " + AopUtils.isAopProxy(testService));
//	String str = "Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do.";
	String str = "Alice was beginning ";
	testService.insertAll(str);
    }
}
