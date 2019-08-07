package com.ymu.javase.stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 原始流。API提供了IntStream、LongStream、DoubleStream等原始流，用来处理原始类型，不必包装成对象流。
 * <P>
 * 如果想要存储short、char、byte、boolean类型，用IntStream。存储float类型，用DoubleStream。
 * @author xinxiamu
 *
 */
public class Demo8 {

	public static void main(String[] args) {
//		IntStream s = IntStream.of(1,1,2,3);
//		int[] values = {5,4,3,2,1};
//		IntStream ss = Arrays.stream(values, 0,5);
////		System.out.println(s.max().getAsInt());
////		System.out.println(ss.max().getAsInt());
//		ss.forEach(System.out::println);
////		s.forEach(System.out::println);
//		s.forEach(i -> {
//			if (i == 3) {
//				System.out.println(i + 7);
//			}});
		
		//产生步长为1的流，不包括上限100。0-99
//		IntStream ir = IntStream.range(0, 100);
//		ir.forEach(System.out::println);
		
		//产生步长为1的流，包括上限100。0-100
//		IntStream irr = IntStream.rangeClosed(0, 100);
//		irr.forEach(System.out::println);
		
		//将对象流转换为原始流
		Stream<String> words = Stream.of("i", "am", "zmt");
		IntStream l = words.mapToInt(String::length);//去对象的长度转成IntStream
		l.forEach(System.out::print);
		
		//原始流转成对象流
		Stream<Integer> irrb = IntStream.rangeClosed(0, 100).boxed();
		irrb.forEach(System.out::print);
	}
}
