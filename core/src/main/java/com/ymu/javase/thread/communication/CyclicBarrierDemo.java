package com.ymu.javase.thread.communication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//CyclicBarrier 是一个用于多线程协同工作的同步工具，它允许一组线程在某个点上汇合，然后同时执行后续任务。所有线程必须等待，直到所有线程都到达这个点，然后才能继续执行。以下是一个示例，演示如何使用 CyclicBarrier：
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int numberOfThreads = 10;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            Thread workThread = new Thread(() -> {
                // 模拟某个工作
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println(">>>>>工作线程到达汇合点=" + Thread.currentThread().getName());
                    barrier.await(); // 等待其他线程到达。所有线程都到达这里后，才会各自继续执行下面代码

                    System.out.println(">>>>>>线程会合后，继续执行=" + Thread.currentThread().getName());
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
            workThread.setName("Thread name-" + (i + 1));
            workThread.start();
        }
    }
}

//总结：
//在这个示例中，我们创建了一个 CyclicBarrier 对象，其构造函数参数指定了需要等待的线程数量，这里设置为3。然后，我们创建了3个工作线程，每个线程执行一些工作，等待在 barrier.await() 处。当每个线程执行到这一点时，它会等待其他线程到达。
//
//一旦所有3个线程都到达这个点，它们同时继续执行后续任务。这种模式非常适用于需要等待一组线程同时达到某个同步点的场景。
//
//CyclicBarrier 可以用于多种情况，如并行计算、分阶段的任务、模拟协同工作等，它提供了一种有力的方式来协调多个线程的执行。
