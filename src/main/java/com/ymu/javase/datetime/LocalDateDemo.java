package com.ymu.javase.datetime;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.WeekFields;
import java.util.*;

import com.ymu.javase.string.StringUtils;
import org.junit.Test;

/**
 * 人类时间。本地时间。
 * LocalDate是一个带有年份、月份、当月天数的日期。
 * @author xinxiamu
 *
 */
public class LocalDateDemo {

	public static void main(String[] args) {
		//创建一个本地日期
//		LocalDate localDate = LocalDate.now();//不可变类
		LocalDate localDate = LocalDate.of(2019, 4, 10);//不可变类
		System.out.println(">>>>year:" + localDate.getYear());
		System.out.println(">>>>moth:" + localDate.getMonthValue() + "==" + localDate.getMonth());
		System.out.println(">>>>dayOfYear:" + localDate.getDayOfYear());//一年中的第几天
		System.out.println(">>>>dayOfMoth:" + localDate.getDayOfMonth());//一月中的第几号
		System.out.println(">>>>>dayOfWeek:" + localDate.getDayOfWeek().getValue() + "===" + localDate.getDayOfWeek());  //一个星期中星期几
		System.out.println(localDate.getMonth().maxLength());//本月共有多少天

		//判断是否为闰年
		System.out.println(localDate.isLeapYear());//可以被4或者400整除但不能被100整除的为闰年

		//返回两个本地日期之间的距离
		LocalDate l1 = LocalDate.now();
		long diffDay = localDate.until(l1, ChronoUnit.DAYS);//天为表示单位，可以以其他的表示
		System.out.println(diffDay);

		//解析日期：
		LocalDate ldt = LocalDate.parse("2007/12/03",DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String s = formatter.format(ldt);
		System.out.println(s);
	}

	@Test
	public void test1() {
		LocalDate nowDate = LocalDate.now();
		System.out.println(nowDate.getDayOfWeek().getValue());
		System.out.println(nowDate.toString());

		LocalDate startDate = LocalDate.of(2020, 11, 9);
		System.out.println(startDate.getDayOfWeek().getValue());
		long diffDay = nowDate.until(startDate, ChronoUnit.DAYS);//天为表示单位，可以以其他的表示
		System.out.println(">>>diff:" + diffDay);

		LocalDate newDate = nowDate.plusDays(20);
		System.out.println(newDate.toString());
	}

	@Test
	public void test2() {
		LocalDate pubD = LocalDate.of(2019, 05, 22);
		int dw = pubD.getDayOfWeek().getValue(); //本周的星期几
		System.out.println(dw);
		LocalDate enDate = pubD.plusDays(7 - dw).plusWeeks(2);
		System.out.println(enDate);
		System.out.println(pubD.plusWeeks(2).toString());
	}

	@Test
	public void  test3() {
		LocalDate nowDate = LocalDate.now();
		nowDate = LocalDate.of(2016,9,14);
		System.out.println(">>>当前日期：" + nowDate.toString() + "====" + nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		//nowDate是一年中的第几周
		System.out.println(">>>一年的第几周：" + nowDate.get(WeekFields.of(DayOfWeek.MONDAY,1).weekOfYear()));

		System.out.println(">>>当周的第几天：" + nowDate.get(WeekFields.of(DayOfWeek.MONDAY,1).dayOfWeek()));

		//nowDate所在周的星期一
		LocalDate newDate = nowDate.plusDays(-(nowDate.getDayOfWeek().getValue()) + 1);
		System.out.println(">>>日期：" + newDate.toString() + "====" + newDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		//当天所在周周一开始，后推四周27天，到第四周星期天
		LocalDate end4 = newDate.plusDays(28 - 1);
		System.out.println(">>>>第四周最后一天：" + end4.toString() + "===" + end4.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		List<String> week4 = new ArrayList<>();
		int weekNum = 5;
		for (int i = 0; i < weekNum * 7; i++) {
			LocalDate nextDate = newDate.plusDays(i); //从0开始，0就是当天
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateStr = formatter.format(nextDate);
			week4.add(dateStr);
		}
		week4.stream().forEach(System.out::println);
	}

	@Test
	public void test4() {
		LocalDate nowDate = LocalDate.now();
		System.out.println(">>>当前日期：" + nowDate.toString() + "====" + nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		//后推三个月
//		LocalDate d5 = nowDate.plusMonths(3);
//		System.out.println(d5.toString());

//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		for (int i = 1; i < 21; i++) {
//			System.out.println("下一天" + formatter.format(nowDate.plusDays(i)));
//			System.out.println("上一天" + formatter.format(nowDate.plusDays(i - 1)));
//		}

		LocalDate wkFirstDate = nowDate.plusDays(-(nowDate.getDayOfWeek().getValue()) + 1);
		System.out.println(">>>>所在周第一天：" + wkFirstDate.toString());
		LocalDate wkEndDate = nowDate.plusDays(7 - nowDate.getDayOfWeek().getValue());
		System.out.println(">>>>所在周最后一天：" + wkEndDate);

		List<List<String>> list = new ArrayList<>();
		List w = new ArrayList();
		for (int i = 0; i < 7 * 4; i++) {
			if ((i + 1) % 7 == 0 ){
				System.out.println(">>>>每周最后一天" + wkFirstDate.plusDays(i));
				w.add(wkFirstDate.plusDays(i));
				list.add(w);
				w = new ArrayList();
			} else {
				System.out.println(wkFirstDate.plusDays(i));
				w.add(wkFirstDate.plusDays(i));
			}
//			System.out.println(wkFirstDate.plusDays(i));
//			list.add(nowDate.plusDays(i).toString());
		}
		System.out.println(list);

	}

	//解析日期
	@Test
	public void test5() {
//		LocalDate ldt = LocalDate.parse("20190828",DateTimeFormatter.ofPattern("yyyyMMdd"));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String s = formatter.format(ldt);
//		System.out.println(s);

		//now的所在周的上一周的周一
		LocalDate now = LocalDate.parse("20190908",DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println(now.toString());
		LocalDate beforW = now.plusWeeks(-1);
		System.out.println(beforW);
		LocalDate firstDateOfWeek = beforW.plusDays(-(beforW.getDayOfWeek().getValue()) + 1);//now的所在周的上一周的周一
		System.out.println(firstDateOfWeek.toString());
		LocalDate wkEndDate = firstDateOfWeek.plusDays(7 - firstDateOfWeek.getDayOfWeek().getValue());
		System.out.println(">>>>所在周最后一天：" + wkEndDate);

		int a = 13;
		int b = 100;
		BigDecimal bb = new BigDecimal((double) a/b);
		double rmoney = bb.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(rmoney);
	}

	//日期比较
	@Test
	public void test6() {
		LocalDate ldt = LocalDate.parse("20190827",DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println(ldt.toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		System.out.println(formatter.format(ldt));

		System.out.println(ldt.equals(LocalDate.now()));
		System.out.println(ldt.isBefore(LocalDate.now()));
		System.out.println(ldt.isAfter(LocalDate.now()));

	}

	@Test
	public void test7() {
		LocalDate now = LocalDate.now();
		System.out.println(now.isAfter(LocalDate.now()));

		LocalDate temp = now;
		LocalDate endDate = now.plusWeeks(3);
		List<String> list = new ArrayList<>();
		while (temp.isBefore(endDate)) {
			list.add(temp.toString());
			temp = temp.plusDays(1);
		}
		System.out.println(list);
	}

	@Test
	public void test8() {
		LocalDate nowDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //DUN/JIT发布时间
		for (int i = 1; i < 21; i++) { //D1后面20天
			//下一天
			LocalDate nextDate = nowDate.plusDays(i);
			String nextDateStr = formatter.format(nextDate);
			System.out.println(">>>>下一天：" + nextDateStr);
			//上一天
                    LocalDate lastDate = nowDate.plusDays(i - 1);
//			LocalDate lastDate = nextDate.plusDays(-1);
			String lastDateStr = formatter.format(lastDate);
			System.out.println(">>>>>上一天：" + lastDateStr);
		}
	}
}
