package com.ymu.javase.thread.communication;

import java.util.concurrent.CountDownLatch;

//CountDownLatch 是一种非常有用的线程协作工具，允许一个或多个线程等待一组操作完成后再执行。以下是一个示例，演示如何使用 CountDownLatch
public class CountDownLatchExample {

    public static void main(String[] args) {
        int numberOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        //创建一组线程
        for (int i = 0; i <numberOfThreads ; i++) {
            int finalI = i;
            Thread workThread = new Thread(() -> {
                try {
                    //模拟耗时工作
                    System.out.println("》》》模拟工作" + finalI);
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown(); //工作线程完成后，计算器减少1
                }
            });
            workThread.start();
        }

        try {
            latch.await(); // 等待所有线程完成
            System.out.println("所有子线程执行完成，主线程继续……");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
