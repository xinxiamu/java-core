package com.ymu.javase.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * ����ʱ�䡣��ʾһ���е�ĳ��ʱ�䣬�磺11:12:00
 * @author xinxiamu
 *
 */
public class LocalTimeDemo {
	
	public static void main(String[] args) {
		//������now����of
		LocalTime localTime = LocalTime.now();
		System.out.println(">>>>hour:" + localTime.getHour()); //ʱ
		System.out.println(">>>>minute:" + localTime.getMinute()); //��
		System.out.println(">>>>second:" + localTime.getSecond()); //��
		System.out.println(">>>>nano:" + localTime.getNano()); //����
		
		//���ԼӼ�ʱ�䣬����LocalDate
		
		//���ԱȽ�ʱ��
		
		//����У��ʱ��
		
		//��ʽ��
		
	}
}
