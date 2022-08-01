package com.ymu.javase.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class Demo1 {
	
	public static void main(String[] args) {
		//==============创建Stream
		//数组转成stream
		String wordsStr = "I am come from china,thank you";
		String[] words = wordsStr.split("[\\P{L}+]");
		Stream<String> s = Stream.of(words);
		long cout = s.filter(w -> w.length() > 4).count();
		System.out.println("单词长度大于4的个数：" + cout);
		
		//另外一种方式
		Stream<String> s1 = Stream.of("I", "am", "come", "from", "china");
		System.out.println("单词长度大于4的个数：" + s1.filter(w -> w.length() > 4).count());
		
		//将数组的一部分转成Stream
		Stream<String> s2 = Arrays.stream(words, 0, 4);
		System.out.println("单词长度大于4的个数：" + s2.filter(w -> w.length() > 3).count());
		
		//生成一个随机数的Stream
		Stream<Double> random = Stream.generate(Math::random);
		
		//处理文件文件行
//		try(Stream<String> lines = Files.lines(path)) {
//			
//		}
	}

}
