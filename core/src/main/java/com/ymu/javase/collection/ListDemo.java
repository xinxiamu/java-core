package com.ymu.javase.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {

        int i = 0;
        a: {

        }

    }

    //可以将一个`Java List`中的所有元素添加到另一个`List`中。您可以使用`List addAll()`方法来做到这一点。结果`List`是两个列表的并集。下面是一个将一个`List`中的所有元素添加到另一个List中的例子:
    @Test
    public void addAllTest() {
        List<String> listSource = new ArrayList<>();

        listSource.add("123");
        listSource.add("456");

        List<String> listDest   = new ArrayList<>();
        listDest.add("123");
        listDest.add("999");

        listDest.addAll(listSource);
        listDest.forEach(s -> System.out.println(">>>:" + s));
    }

    @Test
    public void containTest() {
        List list = new ArrayList();
        list.add(null);

        boolean containsElement = list.contains(null);

        System.out.println("r: " + containsElement);
    }

    @Test
    public void removeTest() {
        List<String> list = new ArrayList<>();

        String element = "first element";
        list.add(null);
        list.add(element);
        list.add(null);
        System.out.println("size1:" + list.size());

        list.remove("first element");

        System.out.println("size2:" + list.size());
    }
}
