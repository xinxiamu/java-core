package com.ymu.javase.thread;

//线程isAlive()方法
//判断当前线程是否处于活跃状态。
//线程活动状态是指：线程已经启动且尚未终止。线程正处于运行或者准备运行的状态。
public class ISAliveDemo_1_5 {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println("begin==" + myThread.isAlive());
        myThread.start();
        System.out.println("end==" + myThread.isAlive()); //这里输出可能是true，也可能是false，取决于线程是否已经运行完成。
        Thread.sleep(5000);
        System.out.println("end1==" + myThread.isAlive()); //main线程休眠后，myThread已经执行完成并终止，所以这里输出false
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run=" + this.isAlive());
        }
    }
}
