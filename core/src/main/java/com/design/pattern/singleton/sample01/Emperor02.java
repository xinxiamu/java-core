package com.design.pattern.singleton.sample01;

/**
 * 线程安全。添加同步锁。
 * <br>
 *     可用，不建议。
 */
public final class Emperor02 {

    private static Emperor02 emperor = null; //定义一个皇帝放在那里，然后给这个皇帝名字

    private Emperor02() {
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }


    public static Emperor02 getInstance() {
        if (emperor == null) {//如果皇帝还没有定义，那就定一个
            synchronized (Emperor02.class) { //只有在emperor==null的时候，才加同步锁，相比在方法上加锁，效率提高一些。缺点：用双重if判断，复杂，容易出错。
                emperor = new Emperor02();
            }

        }
        return emperor;
    }

    //皇帝叫什么名字呀
    public static void emperorInfo(){
        System.out.println("我就是皇帝某某某....");
    }
}
