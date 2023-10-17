package com.ymu.javase.thread.priority;

import java.util.Random;

//线程优先级的规则性
public class PriorityDemo2 {

    public static void main(String[] args) {
        System.out.println("main线程优先级：" + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(8); //主线程优先级设置
        Thread1 thread1 = new Thread1();
//        thread1.setPriority(6);
        thread1.start();
    }

    static class Thread1 extends Thread {

        @Override
        public void run() {
            long beginTime = System.currentTimeMillis();
            long sum = 0L;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10000; j++) {
                    Random random = new Random();
                    random.nextInt();
                    sum = sum + i;
                }
                
            }
            System.out.println("我是线程Thread1，我的优先级是：" + this.getPriority());
        }
    }
    static class Thread2 extends Thread {

        @Override
        public void run() {
            System.out.println("我是线程Thread，我的优先级是：" + this.getPriority());
        }
    }

}
