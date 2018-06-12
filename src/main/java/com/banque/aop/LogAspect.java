package com.banque.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAspect {

	// public Logger logger = Logger.getLogger(LogAspect.class);

	/*
	 * @Before("execution(* com.banque.service.impl.AbstractService+.*(..))") public
	 * void logBefore(JoinPoint jp) { // logger.info("Before " +
	 * jp.getSignature().getName()); System.out.println("Avant " +
	 * jp.getSignature().getName()); }
	 * 
	 * @After("execution(* com.banque.service.impl.AbstractService+.*(..))") public
	 * void logAfter(JoinPoint jp) { // logger.info("After " +
	 * jp.getSignature().getName()); System.out.println("Après " +
	 * jp.getSignature().getName()); }
	 */

	@Around("execution(* com.banque.service.impl.AuthentificationService.authentifier(..))")
	public Object cache(ProceedingJoinPoint pj) throws Throwable {
		System.out.println("Avant " + pj.getSignature().getName());
		Object value = pj.proceed();
		System.out.println("Après " + pj.getSignature().getName());
		return value;
	}

}
