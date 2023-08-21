package com.ymu.javase.thread;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * sleep()方法是在指定的毫秒数内让当前”正在执行的线程“休眠（暂停执行）。”正在执行的线程“是指this.currentThread()返回的线程。
 */
public class SleepDemo_1_6 {

    public static void main(String[] args) {
//        MyThread1 myThread1 = new MyThread1();
//        System.out.println("begin=" + System.currentTimeMillis());
//        myThread1.start();
//        System.out.println("end=" + System.currentTimeMillis());

        MyThread1 myThread2 = new MyThread1();
        System.out.println("begin=" + System.currentTimeMillis());
        myThread2.start();
        System.out.println("end=" + System.currentTimeMillis());
    }

    public static class MyThread1 extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("run threadName=" + this.currentThread().getName() + " begin");
                Thread.sleep(2000); //暂停两秒
                System.out.println("run threadName=" + this.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyThread2 extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("run threadName=" + this.currentThread().getName() + " begin=" + System.currentTimeMillis());
                Thread.sleep(2000); //暂停两秒
                System.out.println("run threadName=" + this.currentThread().getName() + " end=" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
