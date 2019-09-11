package com.ymu.javase.string;

import com.ymu.javase.datetime.DateTimeUtils;

import java.util.Random;
import java.util.UUID;

public final class StringUtils {
	
	public static void main(String[] args) {
		System.out.println(getUUID());
		System.out.println(authCode());
		System.out.println(addZeroFront(7, 33));
		System.out.println(addZeroFront(5, 35));
		System.out.println(addZeroFront(5, 35465));

		String now = DateTimeUtils.now();
		System.out.println(now);
	}
	
	/**
	 * 生成随机唯一字符串uuid。
	 * 
	 * @return uid
	 */
	public static final String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 生成六位验证码字符串。作为短信验证码。
	 */
	public static String authCode() {
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int num = rd.nextInt(9);
			sb.append(num);
		}
		return sb.toString();
	}
	
	/**
	 * 格式化数字。不够digits位数，在前面补零。
	 * @param digits 格式化后位数。
	 * @param num 实际数字。
	 * @return
	 */
	public static String addZeroFront(int digits, int num) {
		String formatStr = String.format("%0"+digits+"d", num);
		return formatStr;
	}

}
