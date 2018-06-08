package com.bridgeit.LoggerService;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class Logger {

	@After("execution(public String getName())")
	public void LoggerAdvice()
	{
		System.out.println(" after advice run. logger menthod start");
	}
	@Before("execution(public String getName())")
	public void LoggerAdviceMessage()
	{
		System.out.println("before advice run. logger menthod start");
	}
	@Around("execution(public String getName())")
	public void loggerAdviceForAround()
	{
		System.out.println("around advice run. logger menthod start");
	}	
}
