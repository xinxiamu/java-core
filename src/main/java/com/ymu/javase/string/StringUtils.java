package com.ymu.javase.string;

import java.util.Random;
import java.util.UUID;

public final class StringUtils {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 生成32位UUID
	 * 
	 * @return uid
	 */
	public static final String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 生成六位随机数
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

}
