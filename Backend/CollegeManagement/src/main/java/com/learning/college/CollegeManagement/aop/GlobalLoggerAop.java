package com.learning.college.CollegeManagement.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GlobalLoggerAop {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalLoggerAop.class);
	
	@Before("execution (* com.learning.college.CollegeManagement.service.CampusService.*(..))")
	public void beforeCampusService(JoinPoint jp) {
		LOGGER.info(jp.getSignature().getName() + " before called");
	}
	
	@After("execution (* com.learning.college.CollegeManagement.service.CampusService.*(..))")
	public void afterCampusService(JoinPoint jp) {
		LOGGER.info(jp.getSignature().getName() + " after called");
	}
	
	@AfterReturning("execution (* com.learning.college.CollegeManagement.service.CampusService.*(..))")
	public void afterReturningCampusService(JoinPoint jp) {
		LOGGER.info(jp.getSignature().getName() + " after returning called");
	}
	
	@AfterThrowing("execution (* com.learning.college.CollegeManagement.service.CampusService.*(..))")
	public void afterThrowingCampusService(JoinPoint jp) {
		LOGGER.info(jp.getSignature().getName() + " after Throwing called");
	}
	
	@Around("execution (* com.learning.college.CollegeManagement.service.CampusService.*(..))")
	public Object aroundToFindTotalTime(ProceedingJoinPoint pjp) throws Throwable {
		
		long start = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long end = System.currentTimeMillis();
		LOGGER.info("Total time taken is : "+ (end-start));
		return obj;
	}

}
