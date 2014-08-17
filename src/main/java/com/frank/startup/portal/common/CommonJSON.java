package com.frank.startup.portal.common;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class CommonJSON implements Serializable {
	
	private static final long serialVersionUID = 6638198187315651901L;
	private String status;
	private String message;
	private Object data;

	public CommonJSON() {
	}

	public CommonJSON(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public CommonJSON(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		JSONObject job = JSONObject.fromObject(this);
		String result = job.toString();
		return result;
	}

}	
