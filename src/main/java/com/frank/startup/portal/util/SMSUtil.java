package com.frank.startup.portal.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @ClassName:     SMSUtil.java
 * @Description:   发送注册短信验证码
 * @author         dell
 * @version        V1.0  
 * @Date           2014年7月15日 下午1:26:13 
 */
public class SMSUtil {

	private static String smsKey = "f606f1fd5a8c289627176e46a0e60068";
	private static final String HOST_URL="http://v.juhe.cn/sms/send";
	public static void setSMSKey(String key){
		smsKey = key;
	}
	
	/**
	 * 
	 * @Title:        sendRegisterMsg 
	 * @Description:  TODO
	 * @param:        code 验证码
	 * @param:        mobile  手机号码
	 * @return:       boolean    true:发送成功 false:发送失败
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年7月15日 下午2:21:53
	 */
	public static boolean sendRegisterMsg(String code,String mobile) {
		HttpClient  httpclient = new HttpClient();
		PostMethod  httpost = new PostMethod(HOST_URL);
		httpost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  

		NameValuePair[] nvps = new NameValuePair[4];
		
		nvps[0]=new NameValuePair("mobile", mobile);
		nvps[1]=new NameValuePair("tpl_id", "5");
		nvps[2]=new NameValuePair("key", smsKey);
		nvps[3]=new NameValuePair("tpl_value", "#code#="+code+"&#company#=聚合数据&#app#=\"帮助卖\"");
		
		try {
			httpost.setRequestBody(nvps);
			httpclient.executeMethod(httpost);
			String response = new String(httpost.getResponseBodyAsString());
			JSONObject object = JSONObject.fromObject(response);
			String errorCode = object.getString("error_code");
			if(errorCode.equals("0")){
				return true;
			}else{
				return false;
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally{
			httpost.releaseConnection();
		}
		return false;
	}
	/** 
	 * @Title:        main 
	 * @Description:  TODO
	 * @param:        @param args    
	 * @return:       void    
	 * @throws 
	 * @author        dell
	 * @Date          2014年7月15日 下午1:26:13 
	 */
	public static void main(String[] args) {
		if( sendRegisterMsg(UUIDGenerator.generateCheckPass(4),"18115687982")){
			System.out.println("短信发送成功");
		}else{
			System.out.println("短信发送失败");
		}
	}
}
