package com.frank.startup.portal.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ClassName:     CommonAspect.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 14:47:21 
 */
public abstract class CommonAspect {

	public abstract void doAfter(JoinPoint jp) throws Exception;
	
	public abstract Object doAround(ProceedingJoinPoint pjp) throws Throwable;
	
	public abstract void doBefore(JoinPoint jp);
	
	public abstract void doThrowing(JoinPoint jp, Throwable ex);
}
