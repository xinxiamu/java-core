package com.ymu.javase.stream;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 聚合操作。
 * 常用地方：求和、求积、字符串追加、求最大值最小值、求并集交集等。
 * @author xinxiamu
 *
 */
public class Demo4 {

	public static void main(String[] args) {
		
		Stream<Integer> values = Stream.of(1,2,3,4,5);
//		Optional<Integer> total = values.reduce((x,y) -> x + y);//所有元素相加，聚合成一个总数。求和。
		Optional<Integer> total = values.reduce(Integer::sum); //更简洁方式
		System.out.println(total.get());
		
		Stream<Integer> values1 = Stream.empty();
//		Stream<Integer> values1 = Stream.of(1,2,3,4);
		Integer total1 = values1.reduce(0,Integer::sum); //当流为空时候，返回默认值0，不需要再处理可选值。
		System.out.println(total1);
		
		//求一个流中所有字符串的总长度
		Stream<String> words = Stream.of("hello","world");
		//(total0, word) -> total0 + word.length() 是一个累加器，并行执行会产生很多个累加值。
		//所以还要(total01, total02) -> total01 + total02，把所有并行累计值再相加得到最终结果。
		int result = words.reduce(0, (total0, word) -> total0 + word.length(), (total01, total02) -> total01 + total02);
		System.out.println(result);
	}
}
