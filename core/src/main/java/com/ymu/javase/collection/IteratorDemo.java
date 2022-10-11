package com.ymu.javase.collection;

import org.junit.Test;

import java.util.*;

public class IteratorDemo {

    public static void main(String[] args) {
    }

    //获取集合的迭代器
    @Test
    public void getIteratorInstance() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        Iterator<String> iterator = list.iterator();// 每个集合带有自己的迭代器
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Iterator<String> iterator2 = set.iterator();
    }

    //修改集合元素，爆并发异常
    @Test
    public void getUpdate() {
        List<String> list = new ArrayList<>();

        list.add("123");
        list.add("456");
        list.add("789");

        Iterator<String> iterator = list.iterator();

        while(iterator.hasNext()) {
            String value = iterator.next();

            if(value.equals("456")){
                list.add("999");
            }
            System.out.println(value);
        }
    }

    @Test
    public void removeItem() {
        List<String> list = new ArrayList<>();

        list.add("123");
        list.add("456");
        list.add("789");

        Iterator<String> iterator = list.iterator();

        while(iterator.hasNext()) {
            String value = iterator.next();

            if(value.equals("456")){
                iterator.remove(); //利用迭代器该方法移除集合中元素
            }
            System.out.println(value);
        }

        System.out.println(list.toString());
    }

    @Test
    public void forEachRemaining(){
        List<String> list = new ArrayList<>();
        list.add("Jane");
        list.add("Heidi");
        list.add("Hannah");

        Iterator<String> iterator = list.iterator();

        iterator.forEachRemaining((element) -> {
            System.out.println(element);
        });
    }

    @Test
    public void listIterator() {
        List<String> list = new ArrayList<>();
        list.add("Jane");
        list.add("Heidi");
        list.add("Hannah");

        ListIterator<String> listIterator = list.listIterator();

        //前往后迭代
        while(listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        //后往前迭代
        while(listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }


}
