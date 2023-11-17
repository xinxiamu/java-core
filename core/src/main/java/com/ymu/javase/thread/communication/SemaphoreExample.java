package com.ymu.javase.thread.communication;

//Semaphore 是一种计数信号量，用于控制同时访问某个资源的线程数量。它可以用于限制并发访问资源的线程数量，以确保线程安全。以下是一个示例，演示如何使用 Semaphore：

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        int numberOfPermits = 3; // 设置许可证数量。限制同时最多三个线程执行
        Semaphore semaphore = new Semaphore(numberOfPermits);

        for (int i = 0; i < 5; i++) {
            Thread workerThread = new Thread(() -> {
                try {
                    semaphore.acquire(); // 获取一个许可证
                    System.out.println("Thread acquired a permit." + Thread.currentThread().getName());
                    // 模拟某个工作
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("Thread released a permit." + Thread.currentThread().getName());
                    semaphore.release(); // 释放一个许可证
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            workerThread.setName("thread name-" + i);
            workerThread.start();
        }
    }
}


//在这个示例中，我们创建了一个 Semaphore 对象，初始许可证的数量是 numberOfPermits，这里设置为3。然后，我们创建了5个工作线程，每个线程执行一些工作。
//
//每个线程在开始执行工作前，首先调用 semaphore.acquire() 来获取一个许可证，如果许可证不可用，线程将等待，直到有许可证可用。一旦获取到许可证，线程可以继续执行工作。完成工作后，线程调用 semaphore.release() 来释放一个许可证，以便其他等待的线程可以继续。
//
//这样，我们限制了同时执行的线程数量，保证不会超过3个线程同时执行。Semaphore 可以用于资源池管理、控制并发访问数据库连接、限制同时访问某个共享资源的线程数量等场景。这是一种有力的工具，用于确保线程安全和控制并发。
