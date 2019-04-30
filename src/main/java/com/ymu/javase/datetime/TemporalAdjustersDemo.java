package com.ymu.javase.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * ʱ��У������
 * @author xinxiamu
 *
 */
public class TemporalAdjustersDemo {
	
	public static void main(String[] args) {
		//����ĳ���µĵ�һ���ܶ�
		LocalDate localDate = LocalDate.of(2019, 4, 1);
		LocalDate firstTuesday = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)); //����һ���µ�LocalDate������ı�ԭ����localDate
		System.out.println(localDate.getDayOfWeek().getValue()); 
		System.out.println(firstTuesday.getDayOfWeek().getValue());
		
		//TODO ���кܶ������ķ��������Բ鿴Դ����Ϥ
		
		
		//TODO ����ʵ��TemporalAdjuster�ӿ���ʵ���Լ���ʱ��У������
	}
}
