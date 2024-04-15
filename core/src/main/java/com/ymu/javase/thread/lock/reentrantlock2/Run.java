package com.ymu.javase.thread.lock.reentrantlock2;

public class Run {

        /**
     * 主函数，程序的入口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        ProducerConsumerExample example = new ProducerConsumerExample();

        // 创建生产者线程
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

        // 创建消费者线程
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

        // 启动生产者线程
        pThread.start();

        // 启动消费者线程
        cThread.start();
    }

}
