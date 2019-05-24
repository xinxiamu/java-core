package com.ymu.javase.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * 本地时间，结合LocalDate和LocalTime。表示一个日期和时间。适合用来存储确定时区的某个时间点。
 * 如果要存储跨时区的，则用ZonedDateTime.
 * @author xinxiamu
 *
 */
public class LocalDateTimeDemo {
	
	public static void main(String[] args) {
		//解析日期并格式化输出
		LocalDateTime ldt = LocalDateTime.parse("2007/12/03 11:12", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String s = formatter.format(ldt);
		System.out.println(s);
		
	}
	
	@Test
	public void test1() {
		//格式化日期
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.toString());
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String str = dateTimeFormatter.format(localDateTime);
		System.out.println(">>>>>>>>:" + str); 
	}
}
