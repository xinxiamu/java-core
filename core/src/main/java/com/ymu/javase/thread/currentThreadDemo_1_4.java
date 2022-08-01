package com.ymu.javase.thread;

//查看当前线程调用信息。
public class currentThreadDemo_1_4 {

    public static void main(String[] args) {
        Thread.currentThread().setName("myMain");

        MyThread myThread = new MyThread();
        myThread.setName("myThread");
        myThread.start();
    }

    public static class MyThread extends Thread {

        public MyThread() {
            System.out.println(Thread.currentThread().getName());
            System.out.println(this.getName());
        }

        @Override
        public void run() {
            super.run();
            System.out.println("---------------------------------------");
            System.out.println(Thread.currentThread().getName());
            System.out.println(this.getName());
        }
    }
}
