package com.ymu.javase.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;

import javax.swing.text.html.parser.Entity;

public final class DateTimeUtils {

	private DateTimeUtils() {
	}

	public static enum FormatterStyle {
		N1("yyyy-MM-dd"), 
		N2("yyyy-MM-dd HH:mm"),
		N3("yyyy-MM-dd HH:mm:ss"),
		M1("yyyy/MM/dd"),
		M2("yyyy/MM/dd HH:mm"),
		M3("yyyy/MM/dd HH:mm:ss");
		
		private final String value;

		FormatterStyle(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * ����date�����ܵ�����һʱ�����
	 * @param date ���dateΪnull�������ǰ���ڡ�
	 * @return
	 */
	public static LocalDate getFirstDayOfWeek(LocalDate date) {
		if (date == null) {
			date = LocalDate.now();
		}
		LocalDate firstDayOfWeek = date.plusDays(-(date.getDayOfWeek().getValue()) + 1);
		return firstDayOfWeek;
	}

	/**
	 * ��ȡ������һ���еĵڼ��ܡ�
	 * @param localDate
	 * @return
	 */
	public static int getWeekOfYear(LocalDate localDate) {
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
		int weekOfYear = localDate.get(weekFields.weekOfYear());
		return weekOfYear;
	}

	/**
	 * �����ַ���ת���ڶ���
	 * @param dateStr �����ַ�����ʽ��2019-08-15����2018/08/15
	 * @param formatterStyle ��ʽ����ʽ�������dateStr��һ�¡�
	 * @return
	 */
	public static LocalDate strToLocalDate(String dateStr, FormatterStyle formatterStyle) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStyle.getValue());
		LocalDate ldt = LocalDate.parse(dateStr, formatter);
		return ldt;
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
	 * @param formatterStyle ��ʽ������
	 * @return ��ʽ����ϵͳʱ�䡣
	 */
	public static String now(FormatterStyle formatterStyle) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStyle.getValue());
		String str = formatter.format(localDateTime);
		return str;
	}

	/**
	 * ��������ʱ�䡣
	 * @param millis ������
	 * @return
	 */
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
