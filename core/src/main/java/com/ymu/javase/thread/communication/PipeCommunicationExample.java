package com.ymu.javase.thread.communication;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//在这个示例中，我们将演示如何使用管道（Pipes）实现线程之间的通信。管道允许一个线程将数据写入管道，而另一个线程可以从管道读取数据。以下是一个使用管道的示例：

//通过管道，线程之间可以进行数据的传递，实现了线程间的通信。这种通信方式特别适用于需要将数据从一个线程传递给另一个线程的情况，例如数据生产者和数据消费者之间的通信。需要确保适当地处理异常，并在适当的时候关闭管道以释放资源。
public class PipeCommunicationExample {

    public static void main(String[] args) {
        try {
            final PipedOutputStream producerOutput = new PipedOutputStream();
            final PipedInputStream consumerInput = new PipedInputStream(producerOutput);

            Thread producerThread = new Thread(() -> {
                try {
                    for (int i = 1; i <= 5; i++) {
                        producerOutput.write(i);
                        System.out.println("Produced: " + i);
                        Thread.sleep(1000);
                    }
                    producerOutput.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread consumerThread = new Thread(() -> {
                try {
                    int data;
                    while ((data = consumerInput.read()) != -1) {
                        System.out.println("Consumed: " + data);
                    }
                    consumerInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            producerThread.start();
            consumerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
