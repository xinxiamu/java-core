package com.design.pattern.singleton.sample01;

/**
 * 普通的单例模式。
 * <br>
 * 中国的历史上一般都是一个朝代一个皇帝，有两个皇帝的话，必然要PK出一个皇帝出来
 * <br>
 *     该单例模式实现方式非线程安全的。在多线程环境下，比如web系统中，每个http请求过来，容器都会创建一个线程，线程一在执行emperor = new Emperor()这句的时候，线程二判断到emperor依然是null，此处，两个线程都可能执行emperor = new Emperor()。这时就破坏单例性了，得到不一样的对象。
 */
public class Emperor {

    private static Emperor emperor = null; //定义一个皇帝放在那里，然后给这个皇帝名字

    private Emperor() {
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }

    public static Emperor getInstance() {
        if (emperor == null) {//如果皇帝还没有定义，那就定一个
            emperor = new Emperor();
        }
        return emperor;
    }

    //皇帝叫什么名字呀
    public static void emperorInfo(){
        System.out.println("我就是皇帝某某某....");
    }
}
