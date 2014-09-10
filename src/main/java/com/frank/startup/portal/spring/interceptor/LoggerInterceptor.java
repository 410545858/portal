package com.frank.startup.portal.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.common.SessionInfo;

/**
 * @ClassName: LoggerInterceptor.java
 * @Description: 拦截器未登录
 * @author FrankWong
 * @version V1.0
 * @Date 2014年8月7日 下午1:47:13
 */
public class LoggerInterceptor implements HandlerInterceptor {

	Logger logger = Logger.getLogger(LoggerInterceptor.class);

//	private List<String> excludedUrls;
//
//	@Autowired
//	public void setExcludedUrls(List<String> excludedUrls) {
//		this.excludedUrls = excludedUrls;
//	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		String requestUri = arg0.getRequestURI().substring(arg0.getContextPath().length());
//        for (String url : excludedUrls) {
//            if (requestUri.equals(url)) {
//                return true;
//            }
//        }
		SessionInfo sessionInfo = (SessionInfo) arg0.getSession().getAttribute(
				Constant.KEY_SESSION_INFO);
		if (null == sessionInfo) {
			arg1.sendRedirect(arg0.getContextPath()+"/login");
			return false;
		}
		return true;
	}

}
