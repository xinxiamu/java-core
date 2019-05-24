package com.ymu.javase.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 本地时间。表示一天中的某个时间，如：11:12:00
 * @author xinxiamu
 *
 */
public class LocalTimeDemo {
	
	public static void main(String[] args) {
		//创建，now或者of
		LocalTime localTime = LocalTime.now();
		System.out.println(">>>>hour:" + localTime.getHour()); //时
		System.out.println(">>>>minute:" + localTime.getMinute()); //分
		System.out.println(">>>>second:" + localTime.getSecond()); //秒
		System.out.println(">>>>nano:" + localTime.getNano()); //纳秒
		
		//可以加减时间，类似LocalDate
		
		//可以比较时间
		
		//可以校正时间
		
		//格式化
		
	}
}
