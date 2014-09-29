package com.frank.startup.portal.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.frank.startup.portal.util.GlobalCacheUtil;

/**
 * @ClassName:     CacheAspect.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 */
@Component("cacheAspect")
public class CacheAspect extends CommonAspect {

	private final static Logger logger = LoggerFactory.getLogger(CacheAspect.class);

	@Override
	public void doAfter(JoinPoint jp) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		Object[] objs = pjp.getArgs();
		StringBuilder sb = new StringBuilder();
		sb.append(methodName);
		if(objs != null && objs.length > 0){
			for(Object o:objs){
				sb.append(o+"-");
			}
		}
		Object result = GlobalCacheUtil.getInstance().get(sb.toString());
		if(result == null){
			Object retVal = pjp.proceed();
			GlobalCacheUtil.getInstance().add(sb.toString(), retVal);
			logger.info("GlobalCache update\t" + sb.toString());
			return retVal;
		}else{
			logger.info("GlobalCache hits\t" + sb.toString());
			return result;
		}
	}

	@Override
	public void doBefore(JoinPoint jp) {
		// TODO Auto-generated method stub
	}

	@Override
	public void doThrowing(JoinPoint jp, Throwable ex) {
		// TODO Auto-generated method stub
	}

}
