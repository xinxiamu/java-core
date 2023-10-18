package com.ymu.javase.thread.communication;

import java.util.LinkedList;

//"等待和通知"（wait/notify）是Java中基本的线程通信机制，允许一个线程等待另一个线程的通知，以实现线程之间的协作。以下是一个示例，演示如何使用wait和notify来进行线程通信：
public class WaitNotifyExample {

    final Object lock = new Object();
    boolean conditionIsMet = false;

    public static void main(String[] args) {

        WaitNotifyExample example = new WaitNotifyExample();

        Thread waitingThread = new Thread(() -> {
            synchronized (example.lock) {
                while (!example.conditionIsMet) {
                    try {
                        System.out.println("Waiting for a signal...");
                        example.lock.wait(); // 等待通知，执行这句，马上释放锁，进入等待。下面代码不执行

                        System.out.println(">>>>这句不执行,直到重新唤醒");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Received a signal!");
            }
        });

        Thread notifyingThread = new Thread(() -> {
            // 模拟某个操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (example.lock) {
                example.conditionIsMet = true;
                example.lock.notify(); // 发送通知，只随机通知一个等待线程。不会马上释放锁，要等待该同步代码块执行完成，才释放锁，然后wait的线程，随机一个获取锁。
                System.out.println("Sent a signal.");
            }
        });

        waitingThread.start();
        notifyingThread.start();

    }
}

//更复杂的一个例子
//在这个示例中，有一个共享的缓冲区 buffer，用于生产者线程和消费者线程之间的通信。当缓冲区已满时，生产者线程等待消费者通知，当缓冲区为空时，消费者线程等待生产者通知。
//
//生产者线程随机生成数据，并将其添加到缓冲区，然后通知消费者线程。消费者线程从缓冲区中取出数据并进行消费，然后通知生产者线程。
//
//这个示例演示了如何使用 wait() 和 notify() 方法来协调生产者和消费者线程，以确保线程之间的通信和协作。这种模式在多线程应用中非常常见，用于分离生产和消费过程。需要注意的是，线程通信和同步是多线程编程中的关键考虑因素，需要小心设计以避免死锁和竞态条件等问题。
class ProducerConsumerExample {
    public static void main(String[] args) {
        final LinkedList<Integer> buffer = new LinkedList<>();
        final int bufferSize = 5;
        final Object lock = new Object();

        Thread producerThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (buffer.size() == bufferSize) {
                        try {
                            lock.wait(); // 缓冲区已满，等待消费者通知
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    int data = (int) (Math.random() * 100);
                    buffer.add(data);
                    System.out.println("Produced: " + data);

                    lock.notify(); // 通知消费者
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (buffer.isEmpty()) {
                        try {
                            lock.wait(); // 缓冲区为空，等待生产者通知
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    int data = buffer.poll();
                    System.out.println("Consumed: " + data);

                    lock.notify(); // 通知生产者
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}

