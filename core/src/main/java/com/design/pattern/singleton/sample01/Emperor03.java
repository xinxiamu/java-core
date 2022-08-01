package com.design.pattern.singleton.sample01;

/**
 * 线程安全。
 * <br>
 *     饿汉式，建议使用。
 *  <br>
 *     缺点：没有lazy loading的效果，从而降低内存的使用率。
 */
public final class Emperor03 {

    private static Emperor03 emperor = new Emperor03(); //定义一个皇帝放在那里，然后给这个皇帝名字

    private Emperor03() {
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }


    public static Emperor03 getInstance() {
        return emperor;
    }

    //皇帝叫什么名字呀
    public static void emperorInfo(){
        System.out.println("我就是皇帝某某某....");
    }
}
