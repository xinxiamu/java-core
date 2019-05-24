package com.ymu.javase.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

import javax.swing.text.html.parser.Entity;

public final class DateTimeUtils {

	private DateTimeUtils() {
	}

	public static enum FormaterStyle {
		N1("yyyy-MM-dd"), 
		N2("yyyy-MM-dd HH:mm"),
		N3("yyyy-MM-dd HH:mm:ss"),
		M1("yyyy/MM/dd"),
		M2("yyyy/MM/dd HH:mm"),
		M3("yyyy/MM/dd HH:mm:ss");
		
		private final String value;
		
		FormaterStyle(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * ��ȡ��ǰʱ�䡣
	 * @return 
	 */
	public static String now() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		String formatStr = formatter.format(localDateTime);
		return formatStr;
	}

	/**
	 * ��ȡ��ǰϵͳʱ�䡣����������ʽ���ء�
	 * @param formaterStyle ��ʽ������
	 * @return ��ʽ����ϵͳʱ�䡣
	 */
	public static String now(FormaterStyle formaterStyle) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formaterStyle.getValue());
		String str = formatter.format(localDateTime);
		return str;
	}
	
	public static long plusTime(long millis) {
		Instant instant = Instant.now();
		Instant newInstant = instant.plusMillis(millis);
		return newInstant.getEpochSecond();
	}
	
	/**
	 * ����ʱ��֮���ʱ���������뷵�ء�
	 * @param startTime
	 * @param endTime 
	 * @return
	 */
	public static long diff(Instant startTime, Instant endTime) {
		//��ʾ����˲ʱ��֮���ʱ����
		Duration timElapsed = Duration.between(startTime, endTime);
		long millis = timElapsed.toMillis();//�����ʾ
		return millis; 
	}


	public static void main(String[] args) {
//		System.out.println(now(FormaterStyle.N1));
//		System.out.println(now(FormaterStyle.N2));
//		System.out.println(now(FormaterStyle.N3));
//		System.out.println(now(FormaterStyle.M1));
//		System.out.println(now(FormaterStyle.M2));
//		System.out.println(now(FormaterStyle.M3));
		
//		plusTime();
		
//		long a = diff(Instant.now(), Instant.now().plusNanos(1_000_000));
//		System.out.println(a);
		
		long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(),
                ChronoUnit.SECONDS);
		System.out.println(secondsFromEpoch);
		Instant i = Instant.ofEpochSecond(1558680128L);
		System.out.println(i.toString()); 
	}
}
