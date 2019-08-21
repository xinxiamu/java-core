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
 * ʱ���ʽ�����ѻ���ʱ��ת�����˿��ö���ʱ�䡣
 * @author xinxiamu
 *
 */
public class DateTimeFormatterDemo {

	public static void main(String[] args) {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();//��ʱ��
		//��Ԥ����ı�׼��ʽ�����DateTimeFormatter.ISO_DATE_TIME�����������ĸ��ַ�ʽ��
		String formatZdtStr = DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
		System.out.println(formatZdtStr);
		
		//���Ի�����صĸ�ʽ���,FormatStyle���м�����ʽ��
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		String formatStr = formatter.format(zonedDateTime);
		System.out.println(formatStr);
		
		//�Զ����ʽ
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String str = formatter2.format(zonedDateTime);
		System.out.println(str);
	}

	/**
	 * ��û�з��ֺ���ֵ�һ������,ÿ������1��͵�1���Ȼ���ǵ�1�ܶ���ÿ�ܵĵ�1��������!�����Բ���������Ҫ��ȡ������,������������ÿ�����1����53��,��1��Ϊ��1��,ÿ�ܴ���һ��ʼ����,��ôΪʲô��?
	 *
	 * ��ΪĬ��ʹ��ISO8061 ��׼,�ñ�׼����������֪���������㲢��һ�����Ե����˻�ȡ������������Ԥ��
	 */
	@Test
	public void test1() {
		//ʹ��DateTimeFormatter��ȡ��ǰ����
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("w");
		//2018���һ��
		System.out.println(LocalDateTime.of(2018, 1, 1, 0, 0, 0).format(dateTimeFormatter));
		//2018�����һ��
		System.out.println(LocalDateTime.of(2018, 12, 31, 0, 0, 0).format(dateTimeFormatter));
		//2019���һ��
		System.out.println(LocalDateTime.of(2019, 1, 1, 0, 0, 0).format(dateTimeFormatter));
		//2019�����һ��
		System.out.println(LocalDateTime.of(2019, 12, 31, 0, 0, 0).format(dateTimeFormatter));
		//������������
		System.out.println(LocalDateTime.of(2019, 4, 6, 0, 0, 0).format(dateTimeFormatter));
		//������������
		System.out.println(LocalDateTime.of(2019, 4, 7, 0, 0, 0).format(dateTimeFormatter));
		//����������һ
		System.out.println(LocalDateTime.of(2019, 4, 8, 0, 0, 0).format(dateTimeFormatter));
	}

	/**
	 * ����test1�Ľ��������������һ���еĵڼ��ܡ�
	 */
	@Test
	public void test2() {
		//ʹ��DateTimeFormatter��ȡ��ǰ����
		WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
		//2018���һ��
		System.out.println(LocalDateTime.of(2018, 1, 1, 0, 0, 0).get(weekFields.weekOfYear()));
		//2018�����һ��
		System.out.println(LocalDateTime.of(2018, 12, 31, 0, 0, 0).get(weekFields.weekOfYear()));
		//2019���һ��
		System.out.println(LocalDateTime.of(2019, 1, 1, 0, 0, 0).get(weekFields.weekOfYear()));
		//2019�����һ��
		System.out.println(LocalDateTime.of(2019, 12, 31, 0, 0, 0).get(weekFields.weekOfYear()));
		//������������
		System.out.println(LocalDateTime.of(2019, 4, 6, 0, 0, 0).get(weekFields.weekOfYear()));
		//������������
		System.out.println(LocalDateTime.of(2019, 4, 7, 0, 0, 0).get(weekFields.weekOfYear()));
		//����������һ
		System.out.println(LocalDateTime.of(2019, 4, 8, 0, 0, 0).get(weekFields.weekOfYear()));
	}
}
