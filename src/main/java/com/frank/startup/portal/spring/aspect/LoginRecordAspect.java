package com.frank.startup.portal.spring.aspect;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @ClassName:     LoginRecordAspect.java
 * @Description:   记录了登录日志
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014年8月17日 下午2:16:12
 */
@Component("loginRecordAspect")
public class LoginRecordAspect extends CommonAspect {

	Logger logger = Logger.getLogger(LoginRecordAspect.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void doBefore(JoinPoint jp) {
		logger.info("doBefore");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		logger.info(getIpAddr(request));
		logger.info("===========Login: " + " start at: "
				+ dateFormat.format(System.currentTimeMillis()));
	}

	@Override
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		long start = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		long end = System.currentTimeMillis();
		long times = end - start;
		logger.info(request.getRemoteAddr());
		logger.info("===========Login Result:  " + retVal.toString()
				+ "  cast time:  " + times + "ms");
		return retVal;
	}

	@Override
	public void doAfter(JoinPoint jp) throws Exception {
		logger.info("===========Login " + " end at: "
				+ dateFormat.format(System.currentTimeMillis())+" "+Arrays.toString(jp.getArgs()));
	}

	@Override
	public void doThrowing(JoinPoint jp, Throwable ex) {
		logger.info("===========Login Exception " +  " throw exception "
				+ ex.getMessage()+"  "+ Arrays.toString(jp.getArgs()));
	}
	
	public String getIpAddr(HttpServletRequest request) {      
		   String ip = request.getHeader("x-forwarded-for");      
		   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
		       ip = request.getHeader("Proxy-Client-IP");      
		   }      
		   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
		       ip = request.getHeader("WL-Proxy-Client-IP");      
		   }      
		   if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
		       ip = request.getRemoteAddr();      
		   }      
		   return ip;      
		} 
}
