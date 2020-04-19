package com.ymu.javase.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1.filter、map和flatMap方法；
 * 2.提取子流和组合流；
 * @author xinxiamu
 *
 */
public class Demo2 {

	public static void main(String[] args) {
		String wordsStr = "I am come from china,thank you";
		String[] words = wordsStr.split("[\\P{L}+]");//拆分成单词
		
		//====filter方法Demo1中已经有介绍
		
		//====对流中的值进行某种转换,map
				//对流中每个元素应用一个函数，并将返回的值放到一个新的流中。
		Stream<String> ws = Stream.of(words).filter(w -> w.length() > 5);
		Stream<String> upperW = ws.map(String::toUpperCase); //转成大写
		Stream<Character> first = ws.map(s -> s.charAt(0)); //取第一个字母
		
		//=====流中流，flatMap
		Stream<Stream<Character>> result = Stream.of(words).map(w -> charStream(w));//[...['a','b','c'],['f','g','r']...]
		Stream<Character> letters = Stream.of(words).flatMap(w -> charStream(w));//[...,'a','b','c','d',....] //展开了
		
		//提取子流，Stream.limit(n),会返回一个包含n个元素的新流（如果原始流的长度小于n，则返回原始流）。这个方法特别适用裁剪指定长度的流。
		Stream<Double> randoms = Stream.generate(Math::random).limit(100); //产生一个包含100个随机数字的流。
		//丢弃前面的n个元素，Stream.skip(n)
		String a = " Hello, World";
		Stream<String> aWords = Stream.of(a.split("[\\\\P{L}+]")).skip(1);
		
		//===将两个流拼接在一起,第一个流的长度必须是有限的。
		Stream<Character> combined = Stream.concat(charStream("Hello"), charStream("World")); //['H','e','l','l','o','W','o','r','l','d']
	}
	
	public static Stream<Character> charStream(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray()) {
			result.add(c);
		}
		return result.stream();
		
	}
}
