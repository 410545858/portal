/**
 * 
 */
package com.frank.startup.portal.quartz;

import org.apache.log4j.Logger;

/**
 * @author FrankWong
 *
 */
public class TaskDemo {

	private  String value1;
	private  String value2;
	private Logger logger =  Logger.getLogger(TaskDemo.class);
	
	public TaskDemo(String value1,String value2){
		this.value1 = value1;
		this.value2 = value2;
		printTest();
	}
	
	private void printTest(){
		logger.info("Job Print:"+value1+"  "+value2);
	}
	
}
