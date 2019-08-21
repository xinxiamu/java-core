package com.ymu.javase.stream;

import java.util.stream.Stream;

/**
 * 收集结果。处理完流后，查看流中的每个元素。
 * @author xinxiamu
 *
 */
public class Demo5 {

	public static void main(String[] args) {
		Stream<String> words = Stream.of("hello", "world");	
//		Object[] array = words.toArray(); //无法在运行时创建一个泛型，所以返回Object[]
		String[] array = words.toArray(String[]::new);//将类型传递给数组的构造函数，返回确定
		for (String string : array) {
			System.out.println(string);
		}
		
		//将流收集到list或者set中
		Stream<String> words2 = Stream.of("i", "am", "zmt");
//		List<String> list = words2.collect(Collectors.toList());
//		ArrayList<String> list = words2.collect(Collectors.toCollection(ArrayList::new));
//		for (String string : list) {
//			System.out.println(string);
//		}
		
//		Set<String> set = words2.collect(Collectors.toSet());//没具体到set类型。
//		HashSet<String> set = words2.collect(Collectors.toCollection(HashSet::new));
//		set.forEach(x -> System.out.println(x));
		
		//将流中所有字符串连接收集起来
//		String resultStr = words2.collect(Collectors.joining());
//		System.out.println(resultStr);	
		//添加分隔符收集起来
//		String resultStr = words2.collect(Collectors.joining(", "));
//		System.out.println(resultStr);
		
		//聚合为总和、平均值、最大值或者最小值
//		IntSummaryStatistics summary = words2.collect(Collectors.summarizingInt(String::length));
//		long sum = summary.getSum();
//		System.out.println(sum);//长度的总和
//		double average = summary.getAverage();//长度平均值 6/3
//		System.out.println(average);
//		int max = summary.getMax(); //最大长度的单词长度
//		System.out.println(max);
		
		//打印或者存到数据库中，不收集，使用foreach，forEachOrdered。调用只会将终止流操作。
//		words2.forEach(System.out::println);//在并行流上执行，不确保顺序的。
		words2.forEachOrdered(System.out::println); //顺序执行，但是会牺牲并行的特性。
		
	}
}
