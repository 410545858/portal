package com.frank.startup.portal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.frank.startup.portal.dto.KeyValuePair;
import com.frank.startup.portal.util.EncryptionMD5;


/**
 * @ClassName:     User.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 23:49:14 
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;//主键ID
	private String uid;
	private String taobaoUid;
	private String loginName;//用户名
	private String password;//密码
	private String nickName;
	private String phone;//电话号码
	private String avatar;// 头像url
	private String email;//邮件地址
	private char isEmailChecked;//邮件地址
	private char type;
	private String status;//状态 1.激活，2.禁用
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private int version;//乐观锁
	private String roleIds;
	private Date lastLoginTime;
	private String lastLoginIp;
	
	private List<Privilege> privilegeList = new ArrayList<Privilege>();
	private List<Menu> menuList = new ArrayList<Menu>();
	
	public User(){
		
	}
	
	public User(Date lastLoginTime,String lastLoginIp,String loginName){
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.loginName = loginName;
	}
	public User(String loginname,String phone,String password,String avatar){
		this.loginName = loginname;
		this.phone = phone;
		this.password = EncryptionMD5.getMD5(password);
		this.avatar = avatar;
	}
	
	
	public String getTaobaoUid() {
		return taobaoUid;
	}
	public void setTaobaoUid(String taobaoUid) {
		this.taobaoUid = taobaoUid;
	}
	public char getIsEmailChecked() {
		return isEmailChecked;
	}
	public void setIsEmailChecked(char isEmailChecked) {
		this.isEmailChecked = isEmailChecked;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", uid=" + uid + ", taobaoUid=" + taobaoUid + ", loginName=" + loginName + ", password=" + password + ", nickName="
				+ nickName + ", phone=" + phone + ", avatar=" + avatar + ", email=" + email + ", isEmailChecked=" + isEmailChecked + ", type=" + type
				+ ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", version=" + version + ", roleIds="
				+ roleIds + ", privilegeList=" + privilegeList + ", menuList=" + menuList + "]";
	}
}
