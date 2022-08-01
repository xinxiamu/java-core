package com.ymu.javase.thread;

//每个java程序（进程），最少都有一个线程，就是main线程。
//main线程在程序启动的时候由jvm创建。
public class MainThread_1_1 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()); //这是个名称为main的线程
    }
}
