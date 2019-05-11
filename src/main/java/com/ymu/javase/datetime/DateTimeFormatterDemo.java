package com.ymu.javase.datetime;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * ʱ���ʽ�����ѻ���ʱ��ת�����˿��ö���ʱ�䡣
 * @author xinxiamu
 *
 */
public class DateTimeFormatterDemo {

	public static void main(String[] args) {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();//��ʱ��
		//��Ԥ����ı�׼��ʽ�����DateTimeFormatter.ISO_DATE_TIME�����������ĸ��ַ�ʽ��
		String formatZdtStr = DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
		System.out.println(formatZdtStr);
		
		//���Ի�����صĸ�ʽ���,FormatStyle���м�����ʽ��
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		String formatStr = formatter.format(zonedDateTime);
		System.out.println(formatStr);
		
		//�Զ����ʽ
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String str = formatter2.format(zonedDateTime);
		System.out.println(str);
	}
}
