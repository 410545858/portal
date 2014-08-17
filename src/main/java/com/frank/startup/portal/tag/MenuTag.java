package com.frank.startup.portal.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.common.SessionInfo;
import com.frank.startup.portal.entity.Menu;
import com.opensymphony.oscache.util.StringUtil;

public class MenuTag extends TagSupport {
	private static Logger logger = Logger.getLogger(MenuTag.class);

	private static final long serialVersionUID = -7586286213014517034L;
	private String id;
	private String cssClass;
	private String childClass;

	private String path;
	private String servletPath;

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		path = request.getContextPath();
		servletPath = request.getServletPath();
		StringBuffer sb = new StringBuffer();
		
		logger.info("path:"+path+"   servletPath:"+servletPath);
		sb.append("<div");
		if (!StringUtil.isEmpty(id)) {
			sb.append(" id=\"" + id + "\" ");
		}
		if (!StringUtil.isEmpty(cssClass)) {
			sb.append(" class=\"" + cssClass + "\"");
		}
		sb.append(">");
		sb.append("<div id=\"menu\">");
		sb.append("<ul id=\"list\" class=\"clear\">");
		if(servletPath.equals("/views/dashboard.jsp")){
			sb.append("<li class=\"list\"> <h2 class=\"h2_1 active\"><a href=\""+path+"/dashboard\">监控</a></h2> </li>");
		}else{
			sb.append("<li class=\"list\"> <h2 ><a href=\""+path+"/dashboard\">监控</a></h2> </li>");

		}
		
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(Constant.KEY_SESSION_INFO);
		if(null!=sessionInfo&&
				null!=sessionInfo){
			List<Menu> menus = sessionInfo.getMenuList();
			for (Menu m : menus) {
				prepareView(m, sb);
			}
		}
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("</div>");
		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			logger.warn("IOException", e);
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	private void prepareView(Menu m, StringBuffer sb) {
		
		if(null!=m.getSubMenus()&&
				!m.getSubMenus().isEmpty()){
			sb.append("<li class=\"list\">").append("<h2 class=\""+m.getUrl().substring(1, m.getUrl().length())+"\">"+m.getName()+"</h2>");
			
			boolean ishas = false;
			StringBuffer sb1 = new StringBuffer();
			for(Menu subMenu:m.getSubMenus()){
				if(servletPath.contains(subMenu.getUrl())){
					sb1.append("<li class=\"list_active\"><a href=\"");
					ishas = true;
				}else{
					sb1.append("<li><a href=\"");
				}
				sb1.append(path)
				.append(subMenu.getUrl())
				.append("\">")
				.append(subMenu.getName())
				.append("</a></li>");
			}
			if(ishas){
				sb.append("<ul style=\"display:block;\">");
			}else{
				sb.append("<ul>");
			}
			sb.append(sb1.toString());
			sb.append("</ul>");
			sb.append("</li>");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getChildClass() {
		return childClass;
	}

	public void setChildClass(String childClass) {
		this.childClass = childClass;
	}

}
