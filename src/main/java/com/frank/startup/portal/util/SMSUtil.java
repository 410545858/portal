package com.frank.startup.portal.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

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
	@SuppressWarnings("deprecation")
	public static boolean sendRegisterMsg(String code,String mobile) {
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(HOST_URL);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("tpl_id", "5"));
		nvps.add(new BasicNameValuePair("key", smsKey));
		nvps.add(new BasicNameValuePair("tpl_value", "#code#="+code+"&#company#=聚合数据&#app#=\"帮助卖\""));
		
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse response = httpclient.execute(httpost);
			String responseString = new BasicResponseHandler().handleResponse(response);
			System.out.println(responseString);
			JSONObject object = JSONObject.fromObject(responseString);
			String errorCode = object.getString("error_code");
			if(errorCode.equals("0")){
				return true;
			}else{
				return false;
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally{
			httpclient.getConnectionManager().shutdown();
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
