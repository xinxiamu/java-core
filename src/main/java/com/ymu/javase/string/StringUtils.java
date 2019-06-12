package com.ymu.javase.string;

import java.util.Random;
import java.util.UUID;

public final class StringUtils {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * ����32λUUID
	 * 
	 * @return uid
	 */
	public static final String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * ������λ�����
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
