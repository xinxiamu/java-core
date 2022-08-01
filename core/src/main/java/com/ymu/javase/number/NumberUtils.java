package com.ymu.javase.number;

import org.junit.Test;

public class NumberUtils {

	public static void main(String[] args) {
//		Number
	}

	// 前面补零
	@Test
	public void test1() {
//		int i = 23414;
//		int i = 5;
		long i = 5;
		String str = String.format("%04d", i); // 定义四位数，前面补零
		System.out.println(str);
	}
}
