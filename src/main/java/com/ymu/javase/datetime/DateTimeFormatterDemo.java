package com.ymu.javase.datetime;

import org.junit.Test;
import sun.util.resources.LocaleData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * 时间格式化。把机器时间转换成人看得懂的时间。
 * @author xinxiamu
 *
 */
public class DateTimeFormatterDemo {

	public static void main(String[] args) {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();//带时区
		//按预定义的标准格式来输出DateTimeFormatter.ISO_DATE_TIME，还有其它的各种方式。
		String formatZdtStr = DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
		System.out.println(formatZdtStr);
		
		//语言环境相关的格式风格,FormatStyle中有几个方式。
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		String formatStr = formatter.format(zonedDateTime);
		System.out.println(formatStr);
		
		//自定义格式
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String str = formatter2.format(zonedDateTime);
		System.out.println(str);
	}

	/**
	 * 有没有发现很奇怪的一件事情,每年的最后1天和第1天居然都是第1周而且每周的第1天是周日!这明显不符合我们要获取的周数,正常的周数是每年最后1天是53周,第1天为第1周,每周从周一开始计算,那么为什么呢?
	 *
	 * 因为默认使用ISO8061 标准,该标准与我们所认知的周数计算并不一致所以导致了获取的周数不符合预期
	 */
	@Test
	public void test1() {
		//使用DateTimeFormatter获取当前周数
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("w");
		//2018年第一天
		System.out.println(LocalDateTime.of(2018, 1, 1, 0, 0, 0).format(dateTimeFormatter));
		//2018年最后一天
		System.out.println(LocalDateTime.of(2018, 12, 31, 0, 0, 0).format(dateTimeFormatter));
		//2019年第一天
		System.out.println(LocalDateTime.of(2019, 1, 1, 0, 0, 0).format(dateTimeFormatter));
		//2019年最后一天
		System.out.println(LocalDateTime.of(2019, 12, 31, 0, 0, 0).format(dateTimeFormatter));
		//这天是星期六
		System.out.println(LocalDateTime.of(2019, 4, 6, 0, 0, 0).format(dateTimeFormatter));
		//这天是星期日
		System.out.println(LocalDateTime.of(2019, 4, 7, 0, 0, 0).format(dateTimeFormatter));
		//这天是星期一
		System.out.println(LocalDateTime.of(2019, 4, 8, 0, 0, 0).format(dateTimeFormatter));
	}

	/**
	 * 上面test1的解决方案。该天在一年中的第几周。
	 */
	@Test
	public void test2() {
		//使用DateTimeFormatter获取当前周数
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
		//2018年第一天
		System.out.println(LocalDateTime.of(2018, 1, 1, 0, 0, 0).get(weekFields.weekOfYear()));
		//2018年最后一天
		System.out.println(LocalDateTime.of(2018, 12, 31, 0, 0, 0).get(weekFields.weekOfYear()));
		//2019年第一天
		System.out.println(LocalDateTime.of(2019, 1, 1, 0, 0, 0).get(weekFields.weekOfYear()));
		//2019年最后一天
		System.out.println(LocalDateTime.of(2019, 12, 31, 0, 0, 0).get(weekFields.weekOfYear()));
		//这天是星期六
		System.out.println(LocalDateTime.of(2019, 4, 6, 0, 0, 0).get(weekFields.weekOfYear()));
		//这天是星期日
		System.out.println(LocalDateTime.of(2019, 4, 7, 0, 0, 0).get(weekFields.weekOfYear()));
		//这天是星期一
		System.out.println(LocalDateTime.of(2019, 4, 8, 0, 0, 0).get(weekFields.weekOfYear()));
	}
}
