package com.frank.startup.portal.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName:     RegexUtil.java
 * @Description:   正则表达式判断工具
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-7-16 下午10:00:45 
 */
public class RegexUtil {

	private static String MOBILE = "^1(3[0-9]|45|47|5[0-35-9]|8[0-9])\\d{8}$";
	private static Pattern p ; 
	
	static{
		p = Pattern.compile(MOBILE);
	}
	public static boolean isPhoneNum(String phone){
		Matcher m = p.matcher(phone);
		return m.matches();
	}
}
