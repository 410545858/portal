package com.frank.startup.portal.dto;

import java.util.Date;
import java.util.List;

import com.frank.startup.portal.entity.Menu;
import com.frank.startup.portal.entity.Privilege;
import com.frank.startup.portal.spring.constraint.Email;
import com.frank.startup.portal.spring.constraint.Phone;
import com.frank.startup.portal.spring.constraint.StringConstraint;
import com.frank.startup.portal.util.EncryptionMD5;


/**
 * @ClassName:     UserDto.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:03:33 
 */

public class UserBean extends BaseBean{

	private static final long serialVersionUID = 3862594560769913383L;
	private int id;//主键ID
	@StringConstraint(min=6,max=20)
	private String loginName;//用户名
	private String password;//密码
	@Phone
	private String phone;//电话号码
	private String avatar;// 头像url
	
	@Email(min=6,max=50)
	private String email;//邮件地址
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private int version;//乐观锁
	@StringConstraint(min=1,max=1)
	private String status;//状态 1.激活，2.禁用
	private String newPassword;//新密码
	private String confirmPassword;//确认新密码
	@StringConstraint(min=1,max=1000)
	private String roleIds;
	
	private List<Privilege> privilegeList;
	private List<Menu> menuList;
	
	public UserBean(){
		
	}
	public UserBean(String loginname,String phone,String password,String avatar){
		this.loginName = loginname;
		this.phone = phone;
		this.password = EncryptionMD5.getMD5(password);
		this.avatar = avatar;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}
	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
