package com.ymu.javase.thread.communication;

import org.junit.Test;

import java.util.concurrent.Exchanger;

//Exchanger 是 Java 多线程编程中的一个同步工具，它允许两个线程在某个点上交换数据。每个线程将数据交给 Exchanger，然后等待另一个线程也放入数据，然后两个线程可以同时取出对方的数据。以下是一个示例，演示如何使用 Exchanger：
//在这个示例中，person1 和 person2 分别模拟两个人，它们使用 Exchanger 交换名字。person1 先告诉自己的名字是 "Alice"，然后等待 person2 也告诉他的名字。person2 同样告诉自己的名字是 "Bob"，然后等待 person1 告诉他的名字。当两个线程都告诉对方自己的名字后，它们同时取出对方告诉的名字。
public class ExchangerExample {

    public static void main(String[] args) throws Exception {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread person1 = new Thread(() -> {
            try {
                String myName = "zmt";
                System.out.println("Person 1: My name is " + myName);
                String exchangedName = exchanger.exchange(myName);
                System.out.println("Person 1: Exchanged name is " + exchangedName);
            } catch (InterruptedException  e) {
                Thread.currentThread().interrupt();
            }
        } );

        Thread person2 = new Thread(() -> {
            try {
                String myName = "hyy";
                System.out.println("Person 2: My name is " + myName);
                String exchangedName = exchanger.exchange(myName);
                System.out.println("Person 2: Exchanged name is " + exchangedName);
            } catch (InterruptedException  e) {
                Thread.currentThread().interrupt();
            }
        } );


        person1.start();
        person2.start();

    }

    public static void testExchange() throws Exception {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread thread1 = new Thread(() -> {
            try {
                String dataToSend = "Data from Thread 1";
                System.out.println("Thread 1 sending: " + dataToSend);
                String receivedData = exchanger.exchange(dataToSend);
                System.out.println("Thread 1 received: " + receivedData);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                String dataToSend = "Data from Thread 2";
                System.out.println("Thread 2 sending: " + dataToSend);
                String receivedData = exchanger.exchange(dataToSend);
                System.out.println("Thread 2 received: " + receivedData);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                String dataToSend = "Data from Thread 3";
                System.out.println("Thread 3 sending: " + dataToSend);
                String receivedData = exchanger.exchange(dataToSend);
                System.out.println("Thread 3 received: " + receivedData);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
