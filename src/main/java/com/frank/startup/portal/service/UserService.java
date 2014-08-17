package com.frank.startup.portal.service;

import java.util.List;
import java.util.Set;

import com.frank.startup.portal.entity.Menu;
import com.frank.startup.portal.entity.Privilege;
import com.frank.startup.portal.entity.Role;
import com.frank.startup.portal.entity.User;

/**
 * @ClassName:     UserService.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 09:55:51 
 */
public interface UserService extends BaseService<User> {

	User login(String loginName,String password);
	
	public List<Menu> getMenuListByPrivileges(Set<Privilege> privileges);
	
	public List<Privilege> getPrivilegesByRoleId(String roleId);
	
	User getByLoginName(String loginName);
	
    public List<Role> getRoleList();
    
}
