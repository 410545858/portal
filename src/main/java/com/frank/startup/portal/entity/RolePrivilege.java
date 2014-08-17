package com.frank.startup.portal.entity;

import java.io.Serializable;

/**
 * @ClassName:     RolePrivilege.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:03:33 
 */

@SuppressWarnings("serial")
public class RolePrivilege extends GenericEntity implements Serializable{

	
	private String roleId;
	private String privilegeId;
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the privilegeId
	 */
	public String getPrivilegeId() {
		return privilegeId;
	}
	/**
	 * @param privilegeId the privilegeId to set
	 */
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}
	
	
}
