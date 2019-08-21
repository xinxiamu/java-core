package com.ymu.javase.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 介绍Optional<T>类，对类T的一个封装类。不会返回null，避免null值。可以阅读Optional类的源码。
 * @author xinxiamu
 *
 */
public class OptionalDemo {

	//使用可选值
	@Test
	public void test1() {
		Stream<String> uniqueWords = Stream.of("melliy", "melliy", "melliy", "gary","gently").distinct(); //只获取一个"melliy",去重
		Optional<String> largest = uniqueWords.filter(w -> w.startsWith("g")).findAny(); //找到所有字母g开头的单词,只要匹配到第一个满足的就结束
		
		List<Object> list = new ArrayList<>();
//		largest.ifPresent(v -> list.add(v)); //当值存在时候，添加到集合中。无返回值
		largest.ifPresent(list::add); //当值存在时候，添加到集合中。无返回值
		System.out.println(list);
		
		//有返回值
		Optional<Object> r = largest.map(list::add);
		System.out.println(r.orElse(null));
		
		String aStr = largest.orElse(""); //当值不存在时候，用空字符串代替。
		System.out.println("aStr:" + aStr);
		
		String bStr = largest.orElseGet(() -> System.getProperty("user.dir"));//当值不存在，计算获取默认值
		System.out.println(bStr);
		
//		String cStr = largest.orElseThrow(NoSuchElementException::new);//值不存在，抛出一个异常
//		System.out.println(cStr);
	}
	
	//创建可选值
	@Test
	public void test2() {
		System.out.println(invers(0d).orElse(0d));
		System.out.println(invers(5d).orElse(0d));
	}
	
	//创建可选值
	@Test
	public void test3() {
		String s = null;
		String s1 = "abc";
		Optional<String> sopt = Optional.ofNullable(s);//s为null，返回Optional.empty
		Optional<String> s1opt = Optional.ofNullable(s1);//不为null，返回Optional.of(object)
		sopt.isPresent();
	}
	
	//使用flatMap组合可选值函数
	//调用一个方法返回Optional<T>,T里面又要个方法g(),就可以组合
	@Test
	public void test4() {
		//方式一
		Optional<Double> a = Optional.of(3d).flatMap(Demo::a).flatMap(Demo::b);//一直组合下去，像流水线
		System.out.println(a.get());
		
		//方式二
		Optional<Double> b = invers(4d).flatMap(Demo::a).flatMap(Demo::b);
		System.out.println(b.get());
	}
	
	private Optional<Double> invers(Double x) {	
		return Optional.ofNullable(x);
	}
	
	static class Demo {
		
		public static Optional<Double> a(Double x) {
			return x == 0 ? Optional.empty() : Optional.of(x);
		}
		
		public static Optional<Double> b(Double x) {
			return x == 0 ? Optional.empty() : Optional.of(x * 10);
		}
		
	}
	
}
