package com.frank.startup.portal.common;

import com.google.common.collect.ImmutableMap;

/**
 * @ClassName:     Constant.java
 * @Description:   系统常量
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:34:46 
 */
public interface Constant {
	
	public final static Integer DEFAULT_PAGE_SIZE = 10;
	
	public final static String KEY_KAPTCHA = "kaptcha";
	
	public static final String KEY_SESSION_INFO = "key_session_info";
	public static final String KEY_REQUEST_MESSAGE = "r_message";
	public static final String KEY_REQUEST_CURRENT_TOP_MENU = "r_current_top_menu"; // 当前用户所选择的Top
	public static final String KEY_REQUEST_CURRENT_LEFT_MENU = "r_current_left_menu"; // 当前用户所选择的Top
	
	public static final String ALIPAY_CONFIG_PARTNER_CODE = "";
	public static final String ALIPAY_CONFIG_KEY = "";
	public static final String TAOBAO_PAY_ACCOUNT = "";
	
	public static final long SMS_EXPIRE_TIME = 60*60*1000;//短信验证码过期时间
	public static final long SMS_RESEND_TIME = 60*1000;//短信发送间隔
	
	public static final int VALID_CODE_LEN = 6; // 验证码长度
	
	public static final ImmutableMap<Integer, String> DAY_MAP = ImmutableMap.<Integer, String>builder() // 时间下拉菜单
			.put(7, "最近一周")
			.put(15, "最近半个月")
			.put(30, "最近一个月")
			.put(60, "最近两个月")
			.build();
}
