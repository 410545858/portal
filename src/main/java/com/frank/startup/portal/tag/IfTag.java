package com.frank.startup.portal.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.common.SessionInfo;
import com.frank.startup.portal.entity.Privilege;

public class IfTag extends BodyTagSupport {
	private static Logger logger = Logger.getLogger(IfTag.class);
	private static final long serialVersionUID = -7586286213014517034L;
	private String funcnum;

	public String getFuncnum() {
		return funcnum;
	}

	public void setFuncnum(String funcnum) {
		this.funcnum = funcnum;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute(Constant.KEY_SESSION_INFO);
		boolean pass = false;
		if (null!=sessionInfo) {
			List<Privilege> privilegeList = sessionInfo.getPrivilegeList();
			for (Privilege privilege : privilegeList) {
				if (privilege.getId().equals(funcnum)) {
					pass = true;
					break;
				}
			}
		}
		if (pass) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			if (bodyContent != null) {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
			}
		} catch (IOException e) {
			logger.warn("IOException", e);
		}
		return EVAL_PAGE;
	}

	public void doInitBody() throws JspTagException {
	}

	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}
}
