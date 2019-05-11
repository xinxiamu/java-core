package com.ymu.javase.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

/**
 * 人类时间。本地时间。
 * LocalDate是一个带有年份、月份、当月天数的日期。
 * @author xinxiamu
 *
 */
public class LocalDateDemo {
	
	public static void main(String[] args) {
		//创建一个本地日期
//		LocalDate localDate = LocalDate.now();//不可变类
		LocalDate localDate = LocalDate.of(2019, 4, 10);//不可变类
		System.out.println(">>>>year:" + localDate.getYear());
		System.out.println(">>>>moth:" + localDate.getMonthValue() + "==" + localDate.getMonth());
		System.out.println(">>>>dayOfYear:" + localDate.getDayOfYear());//一年中的第几天
		System.out.println(">>>>dayOfMoth:" + localDate.getDayOfMonth());//一月中的第几号
		System.out.println(">>>>>dayOfWeek:" + localDate.getDayOfWeek().getValue() + "===" + localDate.getDayOfWeek());  //一个星期中星期几
		System.out.println(localDate.getMonth().maxLength());//本月共有多少天
		
		//判断是否为闰年
		System.out.println(localDate.isLeapYear());//可以被4或者400整除但不能被100整除的为闰年
		
		//返回两个本地日期之间的距离
		LocalDate l1 = LocalDate.now();
		long diffDay = localDate.until(l1, ChronoUnit.DAYS);//天为表示单位，可以以其他的表示
		System.out.println(diffDay);
		
		//解析日期：
		LocalDate ldt = LocalDate.parse("2007/12/03",DateTimeFormatter.ofPattern("yyyy/MM/dd")); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String s = formatter.format(ldt);
		System.out.println(s);
	}
}
