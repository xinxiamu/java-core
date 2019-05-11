package com.ymu.javase.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ����ʱ�䣬���LocalDate��LocalTime����ʾһ�����ں�ʱ�䡣�ʺ������洢ȷ��ʱ����ĳ��ʱ��㡣
 * ���Ҫ�洢��ʱ���ģ�����ZonedDateTime.
 * @author xinxiamu
 *
 */
public class LocalDateTimeDemo {
	
	public static void main(String[] args) {
		//�������ڲ���ʽ�����
		LocalDateTime ldt = LocalDateTime.parse("2007/12/03 11:12", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String s = formatter.format(ldt);
		System.out.println(s);
	}
}
