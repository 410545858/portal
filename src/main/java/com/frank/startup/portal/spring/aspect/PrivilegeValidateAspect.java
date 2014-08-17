package com.frank.startup.portal.spring.aspect;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.common.SessionInfo;
import com.frank.startup.portal.dto.LoginBean;
import com.frank.startup.portal.entity.Privilege;
import com.frank.startup.portal.spring.annotation.FuncNum;

/**
 * @ClassName: PrivilegeAspect.java
 * @Description: 权限校验
 * @author FrankWong
 * @version V1.0
 * @Date 2014-07-10 14:48:38
 */
@Component("privilegeValidateAspect")
public class PrivilegeValidateAspect extends CommonAspect {

	Logger logger = Logger.getLogger(PrivilegeValidateAspect.class);
	
	@Override
	public void doAfter(JoinPoint jp) throws Exception {

	}

	@Override
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("=================================================");
		System.out.println("privilegeValidateAspect");
		System.out.println("=================================================");
		MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
		Class<?>[] parameterTypes = joinPointObject.getMethod()
				.getParameterTypes();
		Method method = pjp.getTarget().getClass()
				.getMethod(pjp.getSignature().getName(), parameterTypes);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		if (method.isAnnotationPresent(FuncNum.class)) {
			SessionInfo sessionInfo = (SessionInfo) request.getSession()
					.getAttribute(Constant.KEY_SESSION_INFO);
			if(null!=sessionInfo){
				FuncNum funcnum = method.getAnnotation(FuncNum.class);
				List<Privilege> privilegeList = sessionInfo.getPrivilegeList();
				for (Privilege privilege : privilegeList) {
					if (privilege.getId().equals(funcnum.value())) {
							return pjp.proceed();
						}
				}
			}
		}
		
		RedirectView rv = new RedirectView(request.getContextPath()+"/login");
		ModelAndView view = new ModelAndView(rv);
		view.addObject(new LoginBean());
		return view;
	}

	@Override
	public void doBefore(JoinPoint jp) {
	}

	@Override
	public void doThrowing(JoinPoint jp, Throwable ex) {
	}

}
