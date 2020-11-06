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

		//�������ڣ�
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
		long diffDay = nowDate.until(startDate, ChronoUnit.DAYS);//��Ϊ��ʾ��λ�������������ı�ʾ
		System.out.println(">>>diff:" + diffDay);

		LocalDate newDate = nowDate.plusDays(20);
		System.out.println(newDate.toString());
	}

	@Test
	public void test2() {
		LocalDate pubD = LocalDate.of(2019, 05, 22);
		int dw = pubD.getDayOfWeek().getValue(); //���ܵ����ڼ�
		System.out.println(dw);
		LocalDate enDate = pubD.plusDays(7 - dw).plusWeeks(2);
		System.out.println(enDate);
		System.out.println(pubD.plusWeeks(2).toString());
	}

	@Test
	public void  test3() {
		LocalDate nowDate = LocalDate.now();
		nowDate = LocalDate.of(2016,9,14);
		System.out.println(">>>��ǰ���ڣ�" + nowDate.toString() + "====" + nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		//nowDate��һ���еĵڼ���
		System.out.println(">>>һ��ĵڼ��ܣ�" + nowDate.get(WeekFields.of(DayOfWeek.MONDAY,1).weekOfYear()));

		System.out.println(">>>���ܵĵڼ��죺" + nowDate.get(WeekFields.of(DayOfWeek.MONDAY,1).dayOfWeek()));

		//nowDate�����ܵ�����һ
		LocalDate newDate = nowDate.plusDays(-(nowDate.getDayOfWeek().getValue()) + 1);
		System.out.println(">>>���ڣ�" + newDate.toString() + "====" + newDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		//������������һ��ʼ����������27�죬��������������
		LocalDate end4 = newDate.plusDays(28 - 1);
		System.out.println(">>>>���������һ�죺" + end4.toString() + "===" + end4.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		List<String> week4 = new ArrayList<>();
		int weekNum = 5;
		for (int i = 0; i < weekNum * 7; i++) {
			LocalDate nextDate = newDate.plusDays(i); //��0��ʼ��0���ǵ���
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateStr = formatter.format(nextDate);
			week4.add(dateStr);
		}
		week4.stream().forEach(System.out::println);
	}

	@Test
	public void test4() {
		LocalDate nowDate = LocalDate.now();
		System.out.println(">>>��ǰ���ڣ�" + nowDate.toString() + "====" + nowDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

		//����������
//		LocalDate d5 = nowDate.plusMonths(3);
//		System.out.println(d5.toString());

//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		for (int i = 1; i < 21; i++) {
//			System.out.println("��һ��" + formatter.format(nowDate.plusDays(i)));
//			System.out.println("��һ��" + formatter.format(nowDate.plusDays(i - 1)));
//		}

		LocalDate wkFirstDate = nowDate.plusDays(-(nowDate.getDayOfWeek().getValue()) + 1);
		System.out.println(">>>>�����ܵ�һ�죺" + wkFirstDate.toString());
		LocalDate wkEndDate = nowDate.plusDays(7 - nowDate.getDayOfWeek().getValue());
		System.out.println(">>>>���������һ�죺" + wkEndDate);

		List<List<String>> list = new ArrayList<>();
		List w = new ArrayList();
		for (int i = 0; i < 7 * 4; i++) {
			if ((i + 1) % 7 == 0 ){
				System.out.println(">>>>ÿ�����һ��" + wkFirstDate.plusDays(i));
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

	//��������
	@Test
	public void test5() {
//		LocalDate ldt = LocalDate.parse("20190828",DateTimeFormatter.ofPattern("yyyyMMdd"));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String s = formatter.format(ldt);
//		System.out.println(s);

		//now�������ܵ���һ�ܵ���һ
		LocalDate now = LocalDate.parse("20190908",DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println(now.toString());
		LocalDate beforW = now.plusWeeks(-1);
		System.out.println(beforW);
		LocalDate firstDateOfWeek = beforW.plusDays(-(beforW.getDayOfWeek().getValue()) + 1);//now�������ܵ���һ�ܵ���һ
		System.out.println(firstDateOfWeek.toString());
		LocalDate wkEndDate = firstDateOfWeek.plusDays(7 - firstDateOfWeek.getDayOfWeek().getValue());
		System.out.println(">>>>���������һ�죺" + wkEndDate);

		int a = 13;
		int b = 100;
		BigDecimal bb = new BigDecimal((double) a/b);
		double rmoney = bb.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(rmoney);
	}

	//���ڱȽ�
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //DUN/JIT����ʱ��
		for (int i = 1; i < 21; i++) { //D1����20��
			//��һ��
			LocalDate nextDate = nowDate.plusDays(i);
			String nextDateStr = formatter.format(nextDate);
			System.out.println(">>>>��һ�죺" + nextDateStr);
			//��һ��
                    LocalDate lastDate = nowDate.plusDays(i - 1);
//			LocalDate lastDate = nextDate.plusDays(-1);
			String lastDateStr = formatter.format(lastDate);
			System.out.println(">>>>>��һ�죺" + lastDateStr);
		}
	}
}
