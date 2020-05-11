package com.design.pattern.singleton.sample01;

/**
 * 线程安全。添加同步锁
 */
public final class Emperor01 {

    private static Emperor01 emperor = null; //定义一个皇帝放在那里，然后给这个皇帝名字

    private Emperor01() {
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }

    /**
     * 加上同步锁，线程安全。但是加锁耗时耗资源。
     * @return
     */
    public static synchronized Emperor01 getInstance() {
        if (emperor == null) {//如果皇帝还没有定义，那就定一个
            emperor = new Emperor01();
        }
        return emperor;
    }

    //皇帝叫什么名字呀
    public static void emperorInfo(){
        System.out.println("我就是皇帝某某某....");
    }
}
