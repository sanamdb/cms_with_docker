package com.learning.college.CollegeManagement.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateCampusIdAop {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateCampusIdAop.class);
	
	@Around("execution (* com.learning.college.CollegeManagement.service.CampusService.findCampusById(..)) && args(id)")
	public Object validateArgsUsingAround(ProceedingJoinPoint pjp, String id) throws Throwable {
		
		if(id.startsWith("-")) {
			LOGGER.info("start with minus");
			id = id.substring(1);
		}
		else 
			LOGGER.info("does not start with minus");
		
		
		Object obj = pjp.proceed(new Object[] {id});
		return obj;
		
	}

}
