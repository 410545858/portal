package com.frank.startup.portal.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.frank.startup.portal.entity.Menu;
import com.frank.startup.portal.entity.Privilege;
import com.frank.startup.portal.entity.User;



/**
 * @ClassName:     MessageConstant.java
 * @Description:   session持久化
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:33:54 
 */
@SuppressWarnings("serial")
public class SessionInfo implements Serializable {
	
	private int id;
	private String phone;
	private String nickName;
	private String avatar ;
	
	private List<Privilege> privilegeList = new ArrayList<Privilege>();
	private List<Menu> menuList = new ArrayList<Menu>();

	private String role;
	
	public void setByUser(User loginUser) {
		id = loginUser.getId();
		phone = loginUser.getPhone();
		nickName = loginUser.getNickName();
		avatar = loginUser.getAvatar();
		privilegeList = loginUser.getPrivilegeList();
		menuList = loginUser.getMenuList();
		role = loginUser.getRoleIds();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the privilegeList
	 */
	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	/**
	 * @param privilegeList the privilegeList to set
	 */
	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	/**
	 * @return the menuList
	 */
	public List<Menu> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "SessionInfo [id=" + id + ", phone=" + phone + ", nickName=" + nickName + ", avatar=" + avatar + ", privilegeList=" + privilegeList
				+ ", menuList=" + menuList + "]";
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
