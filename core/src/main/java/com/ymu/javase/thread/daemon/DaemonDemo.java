package com.ymu.javase.thread.daemon;

//守护线程测试
public class DaemonDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.setName("daemon");
        myThread.setDaemon(true); //设置为守护线程,如果设置成fals，不是守护线程，main执行结束后，用户线程一直执行，jvm就不会停止
        myThread.start();
        Thread.sleep(5000);
        System.out.println("离开线程了，守护线程也要停止了，就不再打印了");

        /*BThread bThread = new BThread();
        bThread.setName("bthread");
        bThread.start();*/
    }
}

class BThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("我是用户线程，永不停止");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

