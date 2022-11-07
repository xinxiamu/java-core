package com.ymu.javase.collection;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;

public class CollectionsDemo {

    /**
     * 反转列表
     */
    @Test
    public void reverseTest() {
        List<String> list = new ArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");

        System.out.println(">>>>>反转前：");
        list.forEach(s -> System.out.println(s));

        Collections.reverse(list);
        System.out.println(">>>>>反转后：");
        list.forEach(s -> System.out.println(s));

    }

    /**
     * 复制元素
     */
    @Test
    public void copyTest() {
        List<String> source = new ArrayList<>();
        Collections.addAll(source, "e1", "e2", "e3");

        //不声明destination长度会报数组越界错误，看源码就知道，要求destination的size大于等source的size
        /*List<String> destination = new ArrayList<>();
        Collections.copy(destination, source);*/

        //正确方式
        List<String> destination = new ArrayList<>(Arrays.asList(new String[source.size()]));
        Collections.copy(destination, source);
        destination.forEach(s -> System.out.println(s));
    }

    //随机洗牌，打乱元素顺序
    @Test
    public void shuffleTest() {
        List<String> list = new ArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        Collections.shuffle(list);
        list.forEach(s -> System.out.println(s));
    }

    //排序,从小到达
    @Test
    public void sortTest() {
        /*List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");*/

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(6);
        list.add(4);

        Collections.sort(list);
        list.forEach(s -> System.out.println(s));
    }

    //找出最小元素
    @Test
    public void minTest() {
        List source = new ArrayList();
        source.add("9");
        source.add("2");
        source.add("3");
        source.add("6");

        String min = (String) Collections.min(source);
        System.out.println(min);
    }

    //找出最大值
    @Test
    public void maxTest() {
        List source = new ArrayList();
        source.add("1");
        source.add("2");
        source.add("3");

        String max = (String) Collections.max(source);
        System.out.println(max);
    }
}
