package com.ymu.javase.thread.lock.reentrantlock1;

import java.util.concurrent.CountDownLatch;

public class BankExample {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(1000);

        CountDownLatch latch = new CountDownLatch(2);

        //存款线程
        Thread depositThread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) { //连续存款5次，每次存入200元
                    account.deposit(200);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                latch.countDown();
            }

        });

        //取款线程
        Thread withdrawThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) { //连续取款10次， 每次取款100元
                    account.withdraw(50);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                latch.countDown();
            }

        });

        depositThread.start();
        withdrawThread.start();

        try {
           /* withdrawThread.join();
            depositThread.join();*/
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(">>>>>>最终账户余额：" + account.getBalance());
    }
}
