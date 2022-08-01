package com.ymu.javase.stream;

import org.jfree.data.json.impl.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 将流的结果收集到map中。
 * @author xinxiamu
 *
 */
public class Demo6 {

	public static void main(String[] args) {
		
		Person person1 = new Person();
		person1.setId(1);
		person1.setName("zmt");
		
		Person person2 = new Person();
		person2.setId(2);
		person2.setName("zjr");
		
		Person person3 = new Person();
		person3.setId(3);
		person3.setName("xrr");
		
		Stream<Person> persons = Stream.of(person1, person2, person3);
//		Map<Integer, Person> result = persons.collect(Collectors.toMap(Person::getId, Function.identity()));//如果多个元素的键相同，则抛出异常
		
		/*Map<Integer, Person> result = persons.collect(
				Collectors.toMap(
						Person::getId, //key
						Function.identity(), //value
						(exitingValue, newValue) -> {throw new IllegalStateException();},//如果两个key一样的话，抛出异常
						TreeMap::new));//指定具体类型*/

		Map<Integer, Map<String, Object>> result = persons.collect(Collectors.toMap(Person::getId, p -> {
			Map<String, Object> m = new HashMap<>();
			m.put("a", "a");
			m.put("b", "b");
			return m;
		}));

		System.out.println("+++++++++++++++++");
		
//		Map<Integer, Person> result = persons.collect(
//				Collectors.toMap(
//						Person::getId, //key
//						Function.identity(), //value
//						(exitingValue, newValue) -> { //如果key冲突，则可以重新指定值
//							Person p = new Person();
//							p.setId(19);//无效，还是2，冲突的key
//							p.setName("zzz");
//							return exitingValue;
//						},//如果两个key一样的话，抛出异常
//						TreeMap::new));//指定具体类型
		
		Set<Integer> keys = result.keySet();
		for (Integer k : keys) {
//			System.out.println("k:" + k + "==name:" + result.get(k).getName());
		}
	}
	
	public static class Person {
		
		private Integer id;
		
		private String name;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}		
	}
}
