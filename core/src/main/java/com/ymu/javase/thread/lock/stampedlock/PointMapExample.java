package com.ymu.javase.thread.lock.stampedlock;

//模拟了一个更新线程和两个读取线程并发访问。updatePoint 方法负责更新点的坐标，而 getPoint 方法负责读取点的坐标。通过使用 StampedLock，读取线程可以在不阻塞更新线程的情况下读取点的坐标，保障了并发访问的安全性。
public class PointMapExample {
    public static void main(String[] args) {
        PointMap pointMap = new PointMap();

        // 更新线程
        Thread updaterThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                pointMap.updatePoint("A", i, i);
                sleep(1000);
            }
        });

        // 读取线程1
        Thread readerThread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                Point point = pointMap.getPoint("A");
                if (point == null) {
                    System.out.println("Reader 1: Point A is null");
                    continue;
                }
                System.out.println("Reader 1: Point A - (" + point.getX() + ", " + point.getY() + ")");
                sleep(800);
            }
        });

        // 读取线程2
        Thread readerThread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                Point point = pointMap.getPoint("A");
                if (point == null) {
                    System.out.println("Reader 2: Point A is null");
                    continue;
                }
                System.out.println("Reader 2: Point A - (" + point.getX() + ", " + point.getY() + ")");
                sleep(1200);
            }
        });

        updaterThread.start();
        readerThread1.start();
        readerThread2.start();

        try {
            updaterThread.join();
            readerThread1.join();
            readerThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

