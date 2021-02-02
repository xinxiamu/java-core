package com.ymu.javase.datetime;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * ʱ���ߣ��������ʱ���
 * <p>
 * ������תһ��24Сʱ��24*60*60=86400�롣
 * ԭ�㣺1970��1��1����ҹ������ҹ��ʼ���㡣
 * @author xinxiamu
 *
 */
public class InstantDemo {

	public static void main(String[] args) {

		//����ĳ���㷨ִ�е�ʱ�䡣�̰߳�ȫ
		//Instant��Duration���ǲ��ɱ��
		Instant start = Instant.now();
		System.out.println(">>>>start:" + start.toString());
		Instant end = Instant.now();
		System.out.println(">>>>end:" + end.toString());
//		diff();//�鿴�÷���ִ��ʱ��
		Duration timElapsed = Duration.between(start, end);//��ʾ����˲ʱ��֮���ʱ����
		long millis = timElapsed.toMillis();//�����ʾ
		System.out.println(">>>>diff:" + millis);

		//����һ��ʱ�����Ƿ��ǵڶ�����10���������Ƚ������㷨�ٶȡ�
		Duration timElapsed1 = Duration.between(start, end);
		boolean b = timElapsed.multipliedBy(10).minus(timElapsed1).isNegative();
		System.out.println(b);

		start.plusMillis(1000);//��1000����
		System.out.println(">>>>start plusMillis:" + start);

		//�Ƚ�����ʱ���ǰ��
		boolean bb = end.isBefore(start);
		System.out.println(bb);
		System.out.println(end.isAfter(start));

		//���ص�ǰʱ������ʾ������ʱ��
		long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(),
                ChronoUnit.SECONDS);
		System.out.println(secondsFromEpoch);

		//���ظ�ʽ����ʾ˲ʱʱ��,����ʱ��ת������ʱ��
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
		                  ldt.getYear(), ldt.getHour(), ldt.getMinute());

		//Ԥ�����뻥���� java.util.Date  java.sql.Date,��ת��Instant
		Date uDate = new Date();
		Instant instant1 = uDate.toInstant();
		java.sql.Date sqlDate = new java.sql.Date(12653564568l);
		sqlDate.toInstant();
	}

	private static void diff() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		Instant i = Instant.ofEpochMilli(1612156399703L);
		System.out.println(i);
	}
}
