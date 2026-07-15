package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class LogAdvice {
    @Before("execution(* org.zerock.service.*.*(..))")
    public void logParams(JoinPoint jp) {
	log.info("---------");
	log.info("logParams");
	Object[] params = jp.getArgs();
	log.info(Arrays.toString(params));
	Object target = jp.getTarget();
	log.info("target : " + target);
	log.info("---------");
    }
}
