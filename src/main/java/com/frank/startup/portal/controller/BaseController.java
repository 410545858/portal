package com.frank.startup.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.common.SessionInfo;
import com.frank.startup.portal.spring.exception.GlobalException;

/**
 * @ClassName:     BaseController.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:35:51 
 */
public class BaseController  implements MessageConstant, Constant  {
	
	private static final Logger logger = Logger.getLogger(BaseController.class);
	
	@ExceptionHandler(value={Exception.class})  
	public String exp(Exception ex,HttpServletRequest request) { 
		if (ex instanceof GlobalException) { 
			logger.error(((GlobalException) ex).getMessage(),ex);
		}else{
			logger.error("BaseController Error",ex);
		}
		
		return "error/403";  
	}
	

	protected SessionInfo getSessionInfo(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute(KEY_SESSION_INFO);
		if (sessionInfo == null) {
			sessionInfo = new SessionInfo();
			session.setAttribute(KEY_SESSION_INFO, sessionInfo);
		}
		return sessionInfo;

	}

	protected void setMessage(String type, String messageCode,
			HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String> messageMap = (Map<String, String>) request
				.getAttribute(KEY_REQUEST_MESSAGE);
		if (messageMap == null) {
			messageMap = new HashMap<String, String>();

		}

		messageMap.put(type, messageCode);
		request.setAttribute(KEY_REQUEST_MESSAGE, messageMap);
	}

	protected Map<String, String> getMessage(String type, String messageCode,
			HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String> messageMap = (Map<String, String>) request
				.getAttribute(KEY_REQUEST_MESSAGE);
		if (messageMap == null) {
			messageMap = new HashMap<String, String>();

		}
		messageMap.put(type, messageCode);
		return messageMap;
	}

	/**
	 * 
	 * @Title:        encodeStr 
	 * @Description:  解决中文乱码问题
	 * @param:        @param str
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年8月1日 上午11:18:00
	 */
	protected  String encodeStr(String str) {
		
		try {  
			if(str == null || str.equals("")){
				return "";
			}
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return str;  
        }  
	}
	protected void setCurrentTopMenu(String menuKey, HttpServletRequest request) {
		request.setAttribute(KEY_REQUEST_CURRENT_TOP_MENU, menuKey);
	}

	protected void setCurrentLeftMenu(String menuKey, HttpServletRequest request) {
		request.setAttribute(KEY_REQUEST_CURRENT_LEFT_MENU, menuKey);
	}

	protected String getCurretnUserName(HttpServletRequest request) {
		String nickName = this.getSessionInfo(request.getSession()).getNickName();
		if (nickName == null || nickName.equals("")) {
			return "noboday";
		}
		return nickName;
	}
	
	protected SessionInfo getCurretnUser(HttpServletRequest request) {
		 return this.getSessionInfo(request.getSession());
	}

	protected void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("rawtypes")
		Enumeration names = session.getAttributeNames();

		while (names.hasMoreElements()) {
			String attrName = (String) names.nextElement();
			session.removeAttribute(attrName);
		}

	}

}
