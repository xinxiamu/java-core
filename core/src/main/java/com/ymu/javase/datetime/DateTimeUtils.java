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
	 * 打印代码执行时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 */
	private static void logExecutionTime(Instant startTime, Instant endTime) {
		Duration duration = Duration.between(startTime, endTime);
		long seconds = duration.getSeconds();
		long millis = duration.toMillis();

		System.out.printf("Code execution started at: %s%n", startTime);
		System.out.printf("Code execution finished at: %s%n", endTime);
		System.out.printf("Total execution time: %s%n seconds, %s%n milliseconds", seconds, millis);
	}

	/**
	 * 返回date所在周的星期一时间对象。
	 * @param date 如果date为null，则代表当前日期。
	 * @return 所在周周一对象。
	 */
	public static LocalDate getFirstDayOfWeek(LocalDate date) {
		if (date == null) {
			date = LocalDate.now();
		}

		return date.plusDays(-(date.getDayOfWeek().getValue()) + 1);
	}

	public static LocalDate getFirstDayOfWeek(String dateStr, FormatterStyle formatterStyle) {
		LocalDate date;
		if (dateStr == null || "".equals(dateStr)) {
			date = LocalDate.now();
		} else {
			date = strToLocalDate(dateStr, formatterStyle);
		}

		return date.plusDays(-(date.getDayOfWeek().getValue()) + 1);
	}

	/**
	 * 返回date所在周的最后一天即周日的对象。
	 * @param date 如果date为null，则代表当前日期。
	 * @return 所在周周日对象。
	 */
	public static LocalDate getEndDayOfWeek(LocalDate date) {
		if (date == null) {
			date = LocalDate.now();
		}

		return date.plusDays(7 - date.getDayOfWeek().getValue());
	}

	public static LocalDate getEndDayOfWeek(String dateStr, FormatterStyle formatterStyle) {
		LocalDate date;
		if (dateStr == null || "".equals(dateStr)) {
			date = LocalDate.now();
		} else {
			date = strToLocalDate(dateStr, formatterStyle);
		}

		return date.plusDays(7 - date.getDayOfWeek().getValue());
	}

	/**
	 * 获取日期是一年中的第几周。
	 * @param localDate
	 * @return
	 */
	public static int getWeekOfYear(LocalDate localDate) {
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
		return localDate.get(weekFields.weekOfYear());
	}

	/**
	 * 日期字符串转日期对象。
	 * @param dateStr 日期字符串格式：2019-08-15或者2018/08/15
	 * @param formatterStyle 格式化格式，必须和dateStr的一致。
	 * @return
	 */
	public static LocalDate strToLocalDate(String dateStr, FormatterStyle formatterStyle) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStyle.getValue());
		return LocalDate.parse(dateStr, formatter);
	}

	/**
	 * 日期按指定格式转成字符串并返回。
	 * @param localDate 待转换日期。
	 * @param formatterStyle 日期字符串格式。
	 * @return
	 */
	public static String LocalDateToStr(LocalDate localDate, FormatterStyle formatterStyle) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStyle.getValue());
		return formatter.format(localDate);
	}

	/**
	 * 获取当前时间。
	 * @return 
	 */
	public static String now() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		return formatter.format(localDateTime);
	}

	/**
	 * 获取当前系统时间。并按给定格式返回。
	 * @param formatterStyle 格式化对象。
	 * @return 格式化的系统时间。
	 */
	public static String now(FormatterStyle formatterStyle) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterStyle.getValue());
		return formatter.format(localDateTime);
	}

	/**
	 * 往后推移时间。
	 * @param millis 毫秒数
	 * @return
	 */
	public static long plusTime(long millis) {
		Instant instant = Instant.now();
		Instant newInstant = instant.plusMillis(millis);
		return newInstant.getEpochSecond();
	}
	
	/**
	 * 两个时间之间的时间量。毫秒返回。
	 * @param startTime
	 * @param endTime 
	 * @return
	 */
	public static long diff(Instant startTime, Instant endTime) {
		//表示两个瞬时点之间的时间量
		Duration timElapsed = Duration.between(startTime, endTime);
		return timElapsed.toMillis();
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
