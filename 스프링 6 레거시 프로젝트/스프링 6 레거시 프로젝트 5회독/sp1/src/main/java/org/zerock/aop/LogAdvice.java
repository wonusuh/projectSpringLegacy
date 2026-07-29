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
	public void logParams(JoinPoint joinPoint) {
		log.info("====================");
		log.info("logParams");

		Object[] params = joinPoint.getArgs();
		log.info(Arrays.toString(params));
		Object target = joinPoint.getTarget();
		log.info(target);

		log.info("====================");
	}

	@Around("execution(* org.zerock.service.*.*(..))")
	public Object logTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.info("====================");
		log.info("logTime");
		long start = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		log.info("====================");
		log.info("TIME : " + (end - start));

		return result;
	}
}
