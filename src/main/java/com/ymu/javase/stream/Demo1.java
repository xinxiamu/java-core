package com.ymu.javase.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class Demo1 {
	
	public static void main(String[] args) {
		//==============����Stream
		//����ת��stream
		String wordsStr = "I am come from china,thank you";
		String[] words = wordsStr.split("[\\P{L}+]");
		Stream<String> s = Stream.of(words);
		long cout = s.filter(w -> w.length() > 4).count();
		System.out.println("���ʳ��ȴ���4�ĸ�����" + cout);
		
		//����һ�ַ�ʽ
		Stream<String> s1 = Stream.of("I", "am", "come", "from", "china");
		System.out.println("���ʳ��ȴ���4�ĸ�����" + s1.filter(w -> w.length() > 4).count());
		
		//�������һ����ת��Stream
		Stream<String> s2 = Arrays.stream(words, 0, 4);
		System.out.println("���ʳ��ȴ���4�ĸ�����" + s2.filter(w -> w.length() > 3).count());
		
		//����һ���������Stream
		Stream<Double> random = Stream.generate(Math::random);
		
		//�����ļ��ļ���
//		try(Stream<String> lines = Files.lines(path)) {
//			
//		}
	}

}
