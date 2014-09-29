package com.frank.startup.portal.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:     GlobalCacheUtil.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-9-19 下午9:20:52 
 */
public class GlobalCacheUtil {

	private  Map<String,GlobalCache> map = new HashMap<String,GlobalCache>();
	private static  GlobalCacheUtil globalCacheUtil = null;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public static GlobalCacheUtil getInstance(){
		if(globalCacheUtil == null){
			globalCacheUtil = new GlobalCacheUtil();
		}
		return globalCacheUtil;
	}
	
	public  void add(String  key,Object result){
		GlobalCache entry = new GlobalCache(sdf.format(new Date()),result);
		map.put(key, entry);
	}
	
	public  Object get(String key){
		GlobalCache entry = map.get(key);
		if(entry == null){
			return null;
		}
		String now = sdf.format(new Date());
		if(now.equals(entry.getDateStr())){//缓存时间为当日有效
			return entry.getResult();
		}
		return null;
	}
	
	class GlobalCache{
		
		private String dateStr;//缓存value 由年月日组成的dateStr 标签和实际缓存值组成，
		private Object result;
		
		public GlobalCache(String dateStr,Object result){
			this.dateStr = dateStr;
			this.result = result;
		}
		/**
		 * @return the dateStr
		 */
		public String getDateStr() {
			return dateStr;
		}

		/**
		 * @param dateStr the dateStr to set
		 */
		public void setDateStr(String dateStr) {
			this.dateStr = dateStr;
		}

		/**
		 * @return the result
		 */
		public Object getResult() {
			return result;
		}
		/**
		 * @param result the result to set
		 */
		public void setResult(Object result) {
			this.result = result;
		}
	}
}

