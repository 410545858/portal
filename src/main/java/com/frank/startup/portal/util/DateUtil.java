package com.frank.startup.portal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @ClassName:     DateUtil.java
 * @Description:   时间格式化工具
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-16 21:39:14 
 */

public class DateUtil {
	/**
	 * 格式[HH:mm]
	 */
	public static String TIME_PATTERN = "HH:mm";
	/**
	 * 格式[yyyy-MM-dd HH:mm:ss.SS]
	 */
	public static String YYYY_MM_DD_HH_MM_SS_SS = "yyyy-MM-dd HH:mm:ss.SS";
	/**
	 * 格式[yyyy-MM-dd HH:mm:ss]
	 */
	public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 格式[yyyy-MM-dd HH:mm]
	 */
	public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	/**
	 * 格式[yyyy-MM-dd]
	 */
	public static String YYYY_MM_DD = "yyyy-MM-dd";
	/**
	 * 格式[yyyyMMdd]
	 */
	public static String YYYYMMDD = "yyyyMMdd";
	/**
	 * 一天的毫秒数
	 */
	public static long DAY = 24L * 60L * 60L * 1000L;
	/**
	 * 一分钟的毫秒数
	 */
	public static long Minute = 60L * 1000L;

	/**
	 * 显式构造函数
	 */
	public DateUtil() {
	}

	/**
	 * 返回当前日期+时间 YYYY_MM_DD_HH_MM_SS
	 * @return 返回该日期
	 */
	public static Date getNow(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(getNowTimeByString(pattern));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 返回当前日期+时间 YYYY_MM_DD_HH_MM_SS
	 * @return 返回该日期
	 */
	public static Date getNowTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(getNowTimeByString(pattern));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();

	}

	/**
	 * 返回当前日期+时间 YYYY_MM_DD_HH_MM_SS
	 * @return 返回该日期的字符串格式
	 */
	public static String getNowTimeByString(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new GregorianCalendar().getTime());

	}

	/**
	 * 返回当天的开始日期+时间 YYYY_MM_DD_HH_MM_SS
	 * @return 返回该日期
	 */
	public static Date getStartTimeOfDay(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(getStartTimeOfDayByString(pattern));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 返回当天的开始日期+时间 YYYY_MM_DD_HH_MM_SS
	 * @return 返回该日期的字符串格式
	 */
	public static String getStartTimeOfDayByString(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title:        getStartTimeOfDayByDate 
	 * @Description:  根据Date获取开始时间
	 * @param:        @param date
	 * @param:        @return    
	 * @return:       Date    
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年7月25日 上午10:08:59
	 */
	public static Date getStartTimeOfDayByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
		
	}
	
	/**
	 * 
	 * @Title:        getEndTimeOfDayByDate 
	 * @Description:  根据Date获取结束时间
	 * @param:        @param date
	 * @param:        @return    
	 * @return:       Date    
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年7月25日 上午10:10:19
	 */
	public static Date getEndTimeOfDayByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
		
	}
	
	/**
	 * 返回当天的最后日期+时间 YYYY_MM_DD_HH_MM_SS
	 * @return 返回该日期
	 */
	public static Date getEndTimeOfDay(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		try {
			return sdf.parse(getEndTimeOfDayByString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 返回指定日期的月份第一天
	 * @param date 指定的日期
	 * @return 返回该日期
	 */
	public static Date getStartDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的月份最后一天
	 * @param date 指定的日期
	 * @return 返回该日期
	 */
	public static Date getEndDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);

		return calendar.getTime();
	}

	/**
	 * 返回指定日期的年
	 * @param date 指定的日期
	 * @return 返回年
	 */
	public static Integer getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回指定日期的月份
	 * @param date 指定的日期
	 * @return 返回月份
	 */
	public static Integer getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 返回指定日期的当月日期
	 * @param date 指定的日期
	 * @return 返回当月日期
	 */
	public static Integer getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 给指定的日期注入一个结束时间23:59:59
	 * @param date 指定的日期
	 * @return 返回当月日期
	 */
	public static Date innerEndTimeOfDay(Date date) {
		if (date == null) {
			return getEndTimeOfDay(YYYY_MM_DD_HH_MM_SS);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 给指定的日期注入一个开始时间00:00:00
	 * @param date 指定的日期
	 * @return 开始时间
	 */
	public static Date innerStartTimeOfDay(Date date) {
		if (date == null) {
			return getStartTimeOfDay(YYYY_MM_DD_HH_MM_SS);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 返回当天的最后日期+时间
	 * @return 返回该日期的字符串格式
	 */
	public static String getEndTimeOfDayByString() {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 转换指定的时间字符串为指定的格式的时间
	 * @param date 指定的时间
	 * @param pattern 指定的格式
	 * @return 格式化后的时间
	 */
	public static Date convertStringTodate(String date, String pattern) {
		if (date == null || pattern == null)
			return null;
		Date returnDate;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			returnDate = sdf.parse(date);
		} catch (ParseException e) {
			returnDate = new Date();
		}
		return returnDate;
	}

	/**
	 * 转换指定的时间为指定的格式的时间字符串
	 * @param date 指定的时间
	 * @param pattern 指定的格式
	 * @return 格式化后的时间字符串
	 */
	public static String convertDateToString(Date date, String pattern) {
		if (date == null || pattern == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 比较2个日期,返回相差天数
	 * @param from 被减数
	 * @param to 减数
	 * @return Long 天数
	 */
	public static Long compareDate(Date from, Date to) {
		Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(from);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(to);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);
        
		return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / DAY;
	}
	
	/**
	 * 比较2个日期,返回相差天数
	 * @param from 被减数
	 * @param to 减数
	 * @return Long 分钟数
	 */
	public static Long compareDateforMinute(Date from, Date to) {
		return (to.getTime() - from.getTime()) / Minute;
	}
	
	/**
	 * 获取某年月有多少天 
	 * @param year 年
	 * @param month 月
	 * @return 天数
	 */
	public static Integer getMothofDays(String year,String month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.valueOf(year));
		cal.set(Calendar.MONTH, (Integer.valueOf(month) - 1));//Java月份从0开始算
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		return dateOfMonth;
	}
	
	/**
	 * 获取某日期是星期几
	 * @param date 日期
	 * @return 星期几[0为星期日，1为星期一，类推]
	 * @throws Exception
	 */
	public static Integer getWeekofDays(String date) throws Exception{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");    
        SimpleDateFormat sdw = new SimpleDateFormat("E");    
        Date d = sd.parse(date);    
        String weektest=sdw.format(d);
        int weekindex=-1;
        if(weektest.equals("星期日")||weektest.equals("Sun")){
        	weekindex=0;
        }else if(weektest.equals("星期一")||weektest.equals("Mon")){
        	weekindex=1;
        }else if(weektest.equals("星期二")||weektest.equals("Tue")){
        	weekindex=2;
        }else if(weektest.equals("星期三")||weektest.equals("Wed")){
        	weekindex=3;
        }else if(weektest.equals("星期四")||weektest.equals("Thu")){
        	weekindex=4;
        }else if(weektest.equals("星期五")||weektest.equals("Fri")){
        	weekindex=5;
        }else if(weektest.equals("星期六")||weektest.equals("Sat")){
        	weekindex=6;
        }else{
        	throw new Exception("系统异常");
        }
        return weekindex;
	}
	
	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	/**
	 * 
	 * @Title:        getMiddleDate 
	 * @Description:  获取两个时间的中间时间
	 * @param:        @param startDate
	 * @param:        @param endDate
	 * @param:        @return    
	 * @return:       Date    
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年7月29日 上午10:11:12
	 */
	public static Date getMiddleDate(Date startDate,Date endDate){
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long middleTime = (startTime+endTime)/2;
		return new Date(middleTime);
	}
	
	/**
	 * 获取下n个月时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getNextMonths(Date date,int months){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, months);
		return now.getTime();
	}
	
}
