package com.ymu.javase.thread.pause;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//下面是一些线程的暂停、恢复的方法


class A {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Thread is running...");
        };

        // 延迟5s后执行任务
        executorService.schedule(task, 5, TimeUnit.SECONDS);

        // 关闭线程池（可选）
        executorService.shutdown();
    }
}

//使用 wait() 和 notify() 需要确保线程正确地获取和释放锁，以防止死锁或其他同步问题。这个示例中的锁对象 lock 确保了线程之间的协调。
//请注意，这种方法也可以用于线程之间的通信，让线程在某些条件下等待并在其他条件下恢复执行。这种方式相对比较安全，因为你可以精确控制线程的暂停和恢复。
class B {
    public static void main(String[] args) {
        Object lock = new Object(); //名为 lock 的共享对象，用作线程之间的锁
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread is waiting...");
                    lock.wait(); // 线程等待，会释放锁
                    System.out.println("Thread has been resumed.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        // 让主线程休眠2分钟
        try {
            Thread.sleep(2 * 60 * 1000); // 2分钟，单位是毫秒
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 恢复线程执行
        synchronized (lock) {
            lock.notify();
        }
    }
}

class C {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        // 让线程休眠1分钟
        try {
            Thread.sleep(6 * 1000); // 1分钟，单位是毫秒
        } catch (
                InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        myThread.pauseThread();

        try {
            Thread.sleep(6 * 1000); // 1分钟，单位是毫秒
        } catch (
                InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 恢复线程执行
        myThread.resumeThread();
    }
}

class MyThread extends Thread {

    private volatile boolean isPaused = false;

    //暂停
    public void pauseThread() {
        isPaused = true;
    }

    public void resumeThread() {
        isPaused = false;
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (isPaused) {
                synchronized (this) {
                    try {
                        wait(); // 当线程被暂停时，等待被唤醒
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } else {
                // 执行工作
                System.out.println("Thread is running...");
            }
        }
    }
}
