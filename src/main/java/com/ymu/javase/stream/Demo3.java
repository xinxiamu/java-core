package com.ymu.javase.stream;

import static org.hamcrest.CoreMatchers.sameInstance;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 1.有状态的转换；
 * 2.简单的聚合方法；
 * @author xinxiamu
 *
 */
public class Demo3 {
	
	public static void main(String[] args) {
		
		//前面两个demo介绍的流转换都是无状态。当从一个已经过滤或者已经映射的流中获取某个元素的时候，结果并不依赖于之前的元素。
		//distinct方法会根据原始流中的元素返回一个具有相同顺序、去掉重复元素的新流，显然该流必须记住之前已读取的元素。
		Stream<String> uniqueWords = Stream.of("melliy", "melliy", "melliy", "gently").distinct(); //只获取一个"melliy"
		
		//Sorted 方法必须遍历整个流，并在产生任意元素之前对它进行排序。
		Stream<String> longestFirst = uniqueWords.sorted(Comparator.comparing(String::length).reversed()); //长度最长的单词排前面。
		
	}
	
	@Test
	public void test1() {
		Stream<String> uniqueWords = Stream.of("melliy", "melliy", "melliy", "gently").distinct(); //只获取一个"melliy"
		//===简单聚合。在流中返回想要结果，终止流。
		Optional<String> largest = uniqueWords.max(String::compareToIgnoreCase);//获取流中最大值
		if (largest.isPresent()) {
			System.out.println("largest:" + largest.get());
		}
	}
	
	@Test
	public void test11() {
		Stream<String> uniqueWords = Stream.of("melliy", "melliy", "melliy", "gary","gently").distinct(); //只获取一个"melliy"
		//===简单聚合。在流中返回想要结果，终止流。
//		Optional<String> largest = uniqueWords.filter(w -> w.startsWith("g")).findFirst(); //找到字母g开头的第一个单词
		Optional<String> largest = uniqueWords.filter(w -> w.startsWith("g")).findAny(); //找到所有字母g开头的单词,只要匹配到第一个满足的就结束
		if (largest.isPresent()) {
			System.out.println("largest:" + largest.get());
		}
	}
	
	@Test
	public void test12() {
		Stream<String> uniqueWords = Stream.of("melliy", "melliy", "melliy", "gary","gently").distinct(); //只获取一个"melliy"
		//===简单聚合。在流中返回想要结果，终止流。
		boolean startWithG = uniqueWords.parallel().anyMatch(w -> w.startsWith("G")); //匹配是否有g开头的单词 。还有allMatch,noneMatch
		System.out.println(startWithG);
	}
}
