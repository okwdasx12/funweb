package com.example.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Aspect
@Component
@Log
public class LogAdvice { // Advice : 여러 주요로직에 적용될 보조기능 클래스
	
	@Before("execution(public * com.example.service.SampleService.*(..))")
	public void logBefore() {
		log.info("================================");
	}
	
	@Before("execution(* com.example.service.SampleService.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	@AfterThrowing(pointcut = "execution(* com.example.service.SampleService.*(..))", throwing = "exception")
	public void logException(Exception exception) {
		log.info("Exception...!!!");
		log.info("exception : " + exception);
	}
	
	@Around("execution(* com.example.service.SampleService.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable{
		
		long startTime = System.currentTimeMillis();
		
		log.info("Target : " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed(); // doAdd() 호출함
		} catch (Throwable e) {
			log.info("예외 발생 ...!!");
		}
		
		long endTime = System.currentTimeMillis();
		
		log.info("method run time : " + (endTime - startTime) + "ms");
		
		return result;
	}
	
	

}






