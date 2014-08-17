package com.frank.startup.portal.util;

import java.util.UUID;

/**
 * @ClassName:     UUIDGenerator.java
 * @Description:   UUID生成器
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:17:37 
 */
public class UUIDGenerator {

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
        "W", "X", "Y", "Z" }; 
	
	public synchronized static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * 
	 * @Title:        generateShortUuid 
	 * @Description:  生成8位短UUID方法
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年8月15日 上午10:27:32
	 */
	public synchronized static String getShortUUID() {  
	    StringBuffer shortBuffer = new StringBuffer();  
	    for (int i = 0; i < 8; i++) {  
	        String str = getUUID().substring(i * 4, i * 4 + 4);  
	        int x = Integer.parseInt(str, 16);  
	        shortBuffer.append(chars[x % 0x3E]);  
	    }  
	    return shortBuffer.toString();  
	  
	} 
	
	/**
	 * 
	 * @Title:        generateCheckPass 
	 * @Description:  生成随机短信验证码
	 * @param:        length 短信验证码长度
	 * @param:        @return    
	 * @return:       String   
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年7月15日 下午3:51:01
	 */
	public synchronized static String generateCheckPass(int length){
		String chars = "0123456789";
		char[] rands = new char[length];
		for (int i = 0; i < length; i++){
			int rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}
		String str = new String(rands);
		return str;
	}
}
