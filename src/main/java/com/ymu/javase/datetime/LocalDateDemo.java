package com.ymu.javase.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

/**
 * ����ʱ�䡣����ʱ�䡣
 * LocalDate��һ��������ݡ��·ݡ��������������ڡ�
 * @author xinxiamu
 *
 */
public class LocalDateDemo {
	
	public static void main(String[] args) {
		//����һ����������
//		LocalDate localDate = LocalDate.now();//���ɱ���
		LocalDate localDate = LocalDate.of(2019, 4, 10);//���ɱ���
		System.out.println(">>>>year:" + localDate.getYear());
		System.out.println(">>>>moth:" + localDate.getMonthValue() + "==" + localDate.getMonth());
		System.out.println(">>>>dayOfYear:" + localDate.getDayOfYear());//һ���еĵڼ���
		System.out.println(">>>>dayOfMoth:" + localDate.getDayOfMonth());//һ���еĵڼ���
		System.out.println(">>>>>dayOfWeek:" + localDate.getDayOfWeek().getValue() + "===" + localDate.getDayOfWeek());  //һ�����������ڼ�
		System.out.println(localDate.getMonth().maxLength());//���¹��ж�����
		
		//�ж��Ƿ�Ϊ����
		System.out.println(localDate.isLeapYear());//���Ա�4����400���������ܱ�100������Ϊ����
		
		//����������������֮��ľ���
		LocalDate l1 = LocalDate.now();
		long diffDay = localDate.until(l1, ChronoUnit.DAYS);//��Ϊ��ʾ��λ�������������ı�ʾ
		System.out.println(diffDay);
	}
}
