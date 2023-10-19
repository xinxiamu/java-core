package com.ymu.javase.thread.communication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//利用阻塞队列，多线程之间通讯协助
//使用阻塞队列，不需要手动实现等待和通知，因为阻塞队列本身会处理这些细节。这使得编写线程安全的生产者-消费者模式变得更加容易和安全。阻塞队列是多线程编程中的重要工具，用于实现线程之间的通信和协作。
public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //创建容量为10的阻塞队列，用作生产者和消费者之间的缓冲区

        //生产者线程不断地将数据放入队列，而消费者线程不断地从队列中取出数据。阻塞队列会在队列为空时阻塞等待消费者，或在队列已满时阻塞等待生产者。

        Thread producerThread  = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i); // 将数据放入队列
                    System.out.println("Produced: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int data = queue.take(); // 从队列中取出数据
                    System.out.println("Consumed: " + data);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();

    }
}
