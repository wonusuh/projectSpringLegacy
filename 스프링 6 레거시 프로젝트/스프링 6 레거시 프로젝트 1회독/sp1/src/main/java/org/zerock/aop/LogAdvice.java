package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
@Component
public class LogAdvice {

	@Before("execution(* org.zerock.service.*.*(..))")
	public void logParams(JoinPoint jp) {
		log.info("--------------------------------------------------");
		log.info("logParams");
		Object[] params = jp.getArgs();
		log.info(Arrays.toString(params));
		Object target = jp.getTarget();
		log.info("target : " + target);
		log.info("--------------------------------------------------");
	}

	@Around("execution(* org.zerock.service.*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
		log.info("--------------------------------------------------");
		log.info("logTime");
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();
		log.info("TIME : " + (end - start));
		log.info("--------------------------------------------------");
		return result;
	}
}
