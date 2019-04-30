package com.ymu.javase.datetime;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * 时间格式化。把机器时间转换成人看得懂的时间。
 * @author xinxiamu
 *
 */
public class DateTimeFormatterDemo {

	public static void main(String[] args) {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();//带时区
		//按预定义的标准格式来输出DateTimeFormatter.ISO_DATE_TIME，还有其它的各种方式。
		String formatZdtStr = DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
		System.out.println(formatZdtStr);
		
		//语言环境相关的格式风格,FormatStyle中有几个方式。
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		String formatStr = formatter.format(zonedDateTime);
		System.out.println(formatStr);
	}
}
