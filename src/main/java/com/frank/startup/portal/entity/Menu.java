package com.frank.startup.portal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Menu.java
 * @Description: TODO
 * @author FrankWong
 * @version V1.0
 * @Date 2014-07-10 13:03:08
 */

@SuppressWarnings("serial")
public class Menu extends GenericEntity implements Serializable {

	private int id;
	private String name;
	private String url;
	private int parent;
	private String privilegeId;
	private int priority;

	private List<Menu> subMenus = new ArrayList<Menu>();

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the parent
	 */
	public int getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(int parent) {
		this.parent = parent;
	}

	/**
	 * @return the privilegeId
	 */
	public String getPrivilegeId() {
		return privilegeId;
	}

	/**
	 * @param privilegeId
	 *            the privilegeId to set
	 */
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the subMenus
	 */
	public List<Menu> getSubMenus() {
		return subMenus;
	}

	/**
	 * @param subMenus
	 *            the subMenus to set
	 */
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
}
