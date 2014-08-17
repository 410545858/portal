package com.frank.startup.portal.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * @ClassName:     EncryptionMD5.java
 * @Description:   MD5加密
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-10 13:19:38 
 */
public class EncryptionMD5 {
	private static final Logger log = Logger.getLogger(EncryptionMD5.class);
	/**
	 * @Title:       getMD5
	 * @Description: 对字符串进行MD5加密
	 * @return:      String   
	 * @throws
	 */
	public static String getMD5(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			log.warn("NoSuchAlgorithmException caught!"+e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.warn(e);
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();
		
		int length = byteArray.length;
		
		for (int i = 0; i < length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
	/**
	 * 
	 * @Title:        getMd5 
	 * @Description:  计算文件Md5值
	 * @param:        file
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年7月17日 上午11:32:50
	 */
	public static String getMd5(File file){
		String md5 = file.getName();
		try {
			md5 = DigestUtils.md5Hex(FileUtils.openInputStream(file));
		} catch (Exception e) {
			md5 = UUIDGenerator.getUUID();
			log.warn("generate md5 failed,file name:" + file.getName());
		}
		return md5;
	}
}
