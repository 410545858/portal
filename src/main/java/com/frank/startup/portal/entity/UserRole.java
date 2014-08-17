package com.frank.startup.portal.entity;

import java.io.Serializable;

/**
 * @ClassName:     UserRole.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:03:33 
 */

@SuppressWarnings("serial")
public class UserRole implements Serializable{
	
	
	private int userId;
	
	private String roleId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
