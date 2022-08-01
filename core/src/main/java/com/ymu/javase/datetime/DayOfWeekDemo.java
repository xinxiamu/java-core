package com.ymu.javase.datetime;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * The Date-Time API provides enums for specifying days of the week and months of the year.
 * @author xinxiamu
 *
 */
public class DayOfWeekDemo {

	public static void main(String[] args) {
		
		//-----------星期几
		System.out.printf("%s%n", DayOfWeek.MONDAY.plus(3));
		
		DayOfWeek dow = DayOfWeek.MONDAY;
		Locale locale = Locale.getDefault();
		System.out.println(dow.getDisplayName(TextStyle.FULL, locale));
		System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
		System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));
		
		//----------------------------月
		System.out.println(">>>>>>月：：");
		System.out.printf("%d%n", Month.FEBRUARY.maxLength());//二月最大天数
		
		Month month = Month.AUGUST;
		Locale locale1 = Locale.getDefault();
		System.out.println(month.getDisplayName(TextStyle.FULL, locale1));
		System.out.println(month.getDisplayName(TextStyle.NARROW, locale1));
		System.out.println(month.getDisplayName(TextStyle.SHORT, locale1));
	}
}
