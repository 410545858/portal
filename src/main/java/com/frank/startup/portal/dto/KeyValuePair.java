package com.frank.startup.portal.dto;

import java.io.Serializable;

import org.apache.commons.httpclient.util.LangUtils;

/**
 * @ClassName:     KeyValuePair.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014年8月4日 下午5:13:26 
 */
public class KeyValuePair implements Serializable{

	private static final long serialVersionUID = 1L;
	private String key=null;
	private String value=null;
	
	public KeyValuePair() {
        this (null, null);
    }
	
	public  KeyValuePair(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the values
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param values the values to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
        return ("key=" + key + ", " + "value=" + value);
    }

    public boolean equals(final Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (object instanceof KeyValuePair) {
        	KeyValuePair that = (KeyValuePair) object;
            return LangUtils.equals(this.key, that.key)
                  && LangUtils.equals(this.value, that.value);
        } else {
            return false;
        }
    }

    public int hashCode() {
        int hash = LangUtils.HASH_SEED;
        hash = LangUtils.hashCode(hash, this.key);
        hash = LangUtils.hashCode(hash, this.value);
        return hash;
    }
}
