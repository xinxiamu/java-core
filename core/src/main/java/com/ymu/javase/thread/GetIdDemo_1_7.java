package com.ymu.javase.thread;

//演示getId()方法。该方法的作用是取得线程的唯一标志。
public class GetIdDemo_1_7 {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " " + thread.getId());
    }
}
