/**
 * 
 */
package com.frank.startup.portal.spring.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.frank.startup.portal.spring.RedisHttpSession;
import com.frank.startup.portal.util.SessionIdGenerator;

/**
 * @author frankwong
 *
 */
public class RedisHttpSessionFilter extends OncePerRequestFilter {

	private static final Logger logger = Logger.getLogger(RedisHttpSessionFilter.class);

	public static final String SESSION_ID_ALIAS = "JSESSIONID";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String sessionId = getCookie(request,SESSION_ID_ALIAS);
		RedisHttpSession session;
		if (StringUtils.isEmpty(sessionId)) {
			setCookie(response);
			session = new RedisHttpSession(sessionId,true);
		}else{
			session = new RedisHttpSession(sessionId,false);
		}
		HttpServletRequest requestWrapper = new RequestWrapper(request, session);
		filterChain.doFilter(requestWrapper, response);
	}

	private static class RequestWrapper extends HttpServletRequestWrapper implements Serializable {

		private static final long serialVersionUID = -8875724242708775407L;

		private HttpSession session;

		public RequestWrapper(HttpServletRequest request, HttpSession session) {
			super(request);
			this.session = session;
		}

		@Override
		public HttpSession getSession(boolean create) {
			return session;
		}

		@Override
		public HttpSession getSession() {
			return session;
		}
	}

	public static String getCookie(HttpServletRequest request, String name) {
		String value = "";
		try {
			Cookie[] cookieArray = request.getCookies();
			if (cookieArray != null) {
				for (Cookie cookie : cookieArray) {
					if (!StringUtils.isEmpty(name) && name.equals(cookie.getName())) {
						return cookie.getValue();
					}
				}
			}
		} catch (Exception e) {
			logger.error("get Cookie error", e);
		}
		return value;
	}
	
	public static void setCookie(HttpServletResponse response){
		Cookie cookie = new Cookie(SESSION_ID_ALIAS, SessionIdGenerator.getInstance().generateSessionId());
		cookie.setMaxAge(60 * 60 * 24 * 365 * 1);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
