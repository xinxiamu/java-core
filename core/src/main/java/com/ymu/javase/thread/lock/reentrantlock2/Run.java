package com.ymu.javase.thread.lock.reentrantlock2;

public class Run {

    public static void main(String[] args) {

        ProducerConsumerExample example = new ProducerConsumerExample();

        Thread pThread = new Thread(() -> {
            try {
                int i = 1;
                while (true) {
                    example.produce(i);
                    Thread.sleep(1000);
                    i++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        pThread.setName("Producer");

        Thread cThread = new Thread(() -> {
            try {
                while (true) {
                    int i = example.consume();
                    System.out.println(">>>>i = " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        cThread.setName("Consumer");

        pThread.start();
        cThread.start();
    }
}
