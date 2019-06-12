package com.ymu.javase.datetime;

import java.time.LocalDateTime;
import java.time.chrono.JapaneseDate;

/**
 * https://docs.oracle.com/javase/tutorial/datetime/iso/nonIso.html
 * @author xinxiamu
 *
 */
public class NonISODateDemo {
	
	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.now();
		JapaneseDate jdate = JapaneseDate.from(localDateTime); //日本时间
		System.out.println(">>>now:" + localDateTime.toString());
	}
}
