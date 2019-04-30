package com.ymu.javase.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间校正器。
 * @author xinxiamu
 *
 */
public class TemporalAdjustersDemo {
	
	public static void main(String[] args) {
		//计算某个月的第一个周二
		LocalDate localDate = LocalDate.of(2019, 4, 1);
		LocalDate firstTuesday = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)); //返回一个新的LocalDate，不会改变原来的localDate
		System.out.println(localDate.getDayOfWeek().getValue()); 
		System.out.println(firstTuesday.getDayOfWeek().getValue());
		
		//TODO 还有很多其他的方法，可以查看源码熟悉
		
		
		//TODO 可以实现TemporalAdjuster接口来实现自己的时间校正器。
	}
}
