package com.ymu.javase.thread.threadlocal;

public class ThreadLocalExample {
    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            // 获取线程1的ThreadLocal计数器，初始值为0
            int counter = threadLocal.get();
            System.out.println("Thread 1: Counter is " + counter);

            // 增加计数器并存储到ThreadLocal
            threadLocal.set(counter + 1);
            System.out.println("Thread 1: Incremented counter to " + (counter + 1));
        });

        Thread thread2 = new Thread(() -> {
            // 获取线程2的ThreadLocal计数器，初始值为0
            int counter = threadLocal.get();
            System.out.println("Thread 2: Counter is " + counter);

            // 增加计数器并存储到ThreadLocal
            threadLocal.set(counter + 1);
            System.out.println("Thread 2: Incremented counter to " + (counter + 1));
        });

        thread1.start();
        thread2.start();
    }
}

