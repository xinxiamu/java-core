package com.ymu.javase.thread.communication;

//线程间通讯，方式一：共享变量
public class SharedVariableCommunicationExample {
    private volatile boolean flag = false; //共享变量，线程之间可见。

    public void setFlagTrue() {
        flag = true;
    }

    public boolean isFlagTrue() {
        return flag;
    }

    public static void main(String[] args) {
        SharedVariableCommunicationExample example = new SharedVariableCommunicationExample();

        Thread writerThread = new Thread(() -> {
            // 模拟某个操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            example.setFlagTrue();
            System.out.println("Flag has been set to true.");
        });

        Thread readerThread = new Thread(() -> {
            while (!example.isFlagTrue()) {
                System.out.println("Waiting for flag to become true...");
                // 这里可以执行其他操作
            }
            System.out.println("Flag is now true.");
        });

        writerThread.start();
        readerThread.start();
    }
}

//以上方法无法保证线程安全。要保证线程安全，需要对变量flag进行同步，避免竞态。

class SharedVariableCommunicationExample1 {
    private volatile boolean flag = false;
    private final Object lock = new Object();

    public void setFlagTrue() {
        //添加同步，线程安全
        synchronized (lock) {
            flag = true;
        }
    }

    public boolean isFlagTrue() {
        //添加同步，线程安全
        synchronized (lock) {
            return flag;
        }
    }

    public static void main(String[] args) {
        SharedVariableCommunicationExample1 example = new SharedVariableCommunicationExample1();

        Thread writerThread = new Thread(() -> {
            // 模拟某个操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            example.setFlagTrue();
            System.out.println("Flag has been set to true.");
        });

        Thread readerThread = new Thread(() -> {
            while (!example.isFlagTrue()) {
                System.out.println("Waiting for flag to become true...");
                // 这里可以执行其他操作
            }
            System.out.println("Flag is now true.");
        });

        writerThread.start();
        readerThread.start();
    }
}


