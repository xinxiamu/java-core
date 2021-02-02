package com.ymu.javase.datetime;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 时间线，代表的是时间戳
 * <p>
 * 地球自转一周24小时，24*60*60=86400秒。
 * 原点：1970年1月1日午夜，从午夜开始计算。
 * @author xinxiamu
 *
 */
public class InstantDemo {

	public static void main(String[] args) {

		//计算某个算法执行的时间。线程安全
		//Instant和Duration都是不可变的
		Instant start = Instant.now();
		System.out.println(">>>>start:" + start.toString());
		Instant end = Instant.now();
		System.out.println(">>>>end:" + end.toString());
//		diff();//查看该方法执行时间
		Duration timElapsed = Duration.between(start, end);//表示两个瞬时点之间的时间量
		long millis = timElapsed.toMillis();//毫秒表示
		System.out.println(">>>>diff:" + millis);

		//检查第一个时间量是否是第二个的10倍。用来比较两个算法速度。
		Duration timElapsed1 = Duration.between(start, end);
		boolean b = timElapsed.multipliedBy(10).minus(timElapsed1).isNegative();
		System.out.println(b);

		start.plusMillis(1000);//加1000毫秒
		System.out.println(">>>>start plusMillis:" + start);

		//比较两个时间点前后
		boolean bb = end.isBefore(start);
		System.out.println(bb);
		System.out.println(end.isAfter(start));

		//返回当前时间的秒表示，绝对时间
		long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(),
                ChronoUnit.SECONDS);
		System.out.println(secondsFromEpoch);

		//本地格式化表示瞬时时间,绝对时间转向人类时间
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
		                  ldt.getYear(), ldt.getHour(), ldt.getMinute());

		//预留代码互操作 java.util.Date  java.sql.Date,都转成Instant
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
