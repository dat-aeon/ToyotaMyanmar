package com.mttl.vlms.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * LoggingAspect
 * 
 *  
 */
@Aspect
public class LoggingAspect {

	private Logger logger = Logger.getLogger(this.getClass());

	@Before("@annotation(ApplyAspect)")
	public void loggingServiceBefore(JoinPoint jointPoint) {
		Signature signature = jointPoint.getSignature();
		String declareTypeName = signature.getDeclaringTypeName();
		String name = signature.getName();
		String methodName = declareTypeName + "." + name + "() ";
		logger.debug(methodName + "method has been started.");
	}

	@AfterReturning("@annotation(ApplyAspect)")
	public void loggingServiceAfterReturn(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String declareTypeName = signature.getDeclaringTypeName();
		String name = signature.getName();
		String methodName = declareTypeName + "." + name + "() ";
		logger.debug(methodName + "method has been successfully finished.");
	}

	@AfterThrowing(pointcut = "@annotation(ApplyAspect)", throwing = "e")
	public void loggingServiceAfterThrow(JoinPoint joinPoint, RuntimeException e) {
		Signature signature = joinPoint.getSignature();
		String declareTypeName = signature.getDeclaringTypeName();
		String name = signature.getName();
		String methodName = declareTypeName + "." + name + "() ";
		logger.error(methodName + "method has been failed.");
		logger.error("Exception is ", e);
	}
}
