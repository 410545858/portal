package com.frank.startup.portal.dto;

import com.frank.startup.portal.spring.constraint.Password;
import com.frank.startup.portal.spring.constraint.Phone;
import com.frank.startup.portal.spring.constraint.ValidCode;

/**
 * @ClassName:     LoginBean.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:03:33 
 */
public class LoginBean extends BaseBean{
	
	private static final long serialVersionUID = -3386682283688735911L;
	
	@Phone
	private String phone;
	@Password(min=32,max=32)
	private String password;
	@ValidCode
	private String validCode;
	
	private String loginName;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
