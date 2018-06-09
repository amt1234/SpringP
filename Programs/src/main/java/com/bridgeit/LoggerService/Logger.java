package com.bridgeit.LoggerService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class Logger {

	@After("execution(public String getName())")
	public void LoggerAdvice(JoinPoint jp)
	{
		System.out.println(" after advice run. logger menthod start");
		System.out.println("-------------------------------------");
	}
	@Before("execution(public String getName())")
	public void LoggerAdviceMessage()
	{
		System.out.println("before advice run. logger menthod start");
		System.out.println("-------------------------------------");
	}
	
	@Around("execution(public String getName())")
	public Object loggerAdviceForAround(ProceedingJoinPoint pjp)
	{
		System.out.println("around advice run . logger menthod start");
		Object v = null; 
		try {
		v = pjp.proceed( pjp.getArgs()) ;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("around advice run . logger menthod end");
		return v;
	}	
}
