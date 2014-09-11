/**
 * 
 */
package com.frank.startup.portal.spring;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.frank.startup.portal.service.RedisSessionService;

/**
 * @author frankwong
 *
 */
public class RedisHttpSession implements HttpSession {

	private  String sessionId;
	
	private RedisSessionService redisSessionService;
	
	
	/**
	 * @param id
	 * @param init true:初始化 false:已经存在
	 */
	public RedisHttpSession(final String sessionId,RedisSessionService redisSessionService){
		this.redisSessionService = redisSessionService;
		this.sessionId = sessionId;
		redisSessionService.freshSession(sessionId);
	}
	
	@Override
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getId() {
		return sessionId;
	}

	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		return redisSessionService.get(sessionId, name);
	}

	@Override
	public Object getValue(String name) {
		return getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return redisSessionService.getAttributeNames(sessionId);
	}

	@Override
	public String[] getValueNames() {
		return redisSessionService.getValueNames(sessionId);
	}

	@Override
	public void setAttribute(String name, Object value) {
		redisSessionService.set(sessionId, name, value);
	}

	@Override
	public void putValue(String name, Object value) {
		setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeValue(String name) {
		removeAttribute(name);
	}

	@Override
	public void invalidate() {
		redisSessionService.clear(sessionId);
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
