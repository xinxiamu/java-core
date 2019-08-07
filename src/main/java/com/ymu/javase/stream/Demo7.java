package com.ymu.javase.stream;

import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sun.awt.www.content.audio.x_aiff;

/**
 * 分组和分片。
 * @author xinxiamu
 *
 */
public class Demo7 {

	public static void main(String[] args) {
		 Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		 //分组，把相同属性的对象分组
//		 Map<String, List<Locale>> countyToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));//Locale::getCountry 进行分组的分类函数
//		 List<Locale> swissLocales = countyToLocales.get("CH");
//		 for (Locale locale : swissLocales) {
//			System.out.println(locale.getLanguage());
//		}
		 
		 //分组后，指定v的具体类型，为set，而不是list
//		 Map<String, Set<Locale>> countyToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
//		 Set<Locale> swissLocales = countyToLocales.get("CH");
//		 for (Locale locale : swissLocales) {
//			 System.out.println(locale.getLanguage());
//		 }
		 
		 //计算每个国家拥有多少种语言Collectors.counting()
//		 Map<String, Long> result = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.counting()));
//		 Long count = result.get("CH");
//		 System.out.println(count);
		 
		 //计算每个国家下，语言的个数。Collectors.summingInt
//		 Map<String, Integer> r = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.summingInt(x -> x.getLanguage().length())));
//		 Set<String> keys = r.keySet();
//		 for (String key : keys) {
//			 if ("CH".equals(key)) {
//				System.out.println("k:" + key + "==v:" + r.get(key));
//			}
//		}
		 
		 //返回的是IntSummaryStatistics，还可以计算总数、平均值等
//		 Map<String, IntSummaryStatistics> r = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.summarizingInt(x -> x.getLanguage().length())));
//		 Set<String> keys = r.keySet();
//		 for (String key : keys) {
//			 if ("CH".equals(key)) {
//				System.out.println("k:" + key + "==v:" + r.get(key).getSum());
//			}
//		}
		 
		 //产生每个国家中，语言种类最多的
//		 Map<String, Optional<Locale>> r = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.maxBy(Comparator.comparing(Locale::getLanguage))));
//		 Optional<Locale> ch = r.get("CH");
//		 System.out.println(ch.get().getLanguage());
		 
		 //每个国家下，按语言聚合，组装成set
//		 Map<String, Set<String>> r = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.mapping(Locale::getLanguage, Collectors.toSet())));
//		 Set<String> keysSet = r.keySet();
//		 for (String key : keysSet) {
////			 if ("CH".equals(key)) {
////				 System.out.println("k:" + key + "==v:" + r.get(key));
////			}
//			System.out.println("k:" + key + "==v:" + r.get(key));
//		}
		 
		 //按国家分组，把每个国家的语言聚合并用逗号隔开
		//k:CH==v:gsw ,de ,pt ,fr ,rm ,it ,wae ,en
//		 Map<String, String> r = locales.collect(Collectors.groupingBy(Locale::getCountry, Collectors.mapping(Locale::getLanguage, Collectors.joining(" ,"))));
//		 Set<String> keysSet = r.keySet();
//		 for (String key : keysSet) {
//			 if ("CH".equals(key)) {
//				 System.out.println("k:" + key + "==v:" + r.get(key));
//			}
////			System.out.println("k:" + key + "==v:" + r.get(key));
//		}
	
	}
}
