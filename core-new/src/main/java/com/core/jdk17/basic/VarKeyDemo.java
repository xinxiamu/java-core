package com.core.jdk17.basic;

import java.util.List;

//演示关键字var使用
//从 Java SE 10 开始可使用
//使用限制
//您只能将其用于方法、构造函数和初始化块中声明的局部变量。
//var不能用于字段，也不能用于方法或构造函数参数。
//声明变量时，编译器必须能够选择类型。由于null没有类型，该变量必须有一个初始值设定项。
public class VarKeyDemo {

    //private var name = "Sue"; //错误使用，不能用于字段

    public static void test1() {
        //var message; //错误，必须有初始值
        var message = "dddddddd";
        message = "abc";
        System.out.println(message);

        var list = List.of("one", "two", "three", "four");
        for (var element: list) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
