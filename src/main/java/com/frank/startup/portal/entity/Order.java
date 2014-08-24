package com.frank.startup.portal.entity;

import java.util.Date;

/**
 * @ClassName:     Order.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014年8月11日 下午4:51:31 
 */
public class Order extends GenericEntity {
	private int id;
	private String orderId;
	private String trade_id;
	private int userId;
	private String status;
	private double price;
	private char type;
	private Date createTime;
	private Date finishTime;
	private String updater;
	private String description;
	private String isDeleted;
	
	private String nickName;
	
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
