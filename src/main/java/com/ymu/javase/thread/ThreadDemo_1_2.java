package com.ymu.javase.thread;

import org.junit.Test;

//线程的实现有两种方式，一种是继承Thread类，一种是继承Runnable接口。实际上Thread也是实现了Runnable接口。
//cpu对子线程的调用具有不确定性和随机性。
//线程启动执行start方法了，并不代表线程就是按照start顺序启动的。这个也有随机性。
public class ThreadDemo_1_2 {

    public static void main(String[] args) {

        //线程是一个子任务，CPU以不确定的方式，或者说随机的时间来调用线程中的run方法。
        //下面展示了CPU对main线程和myThread线程的调用是随机的。多运行几次，你总能看到不一样的输出结果，以不确定的随机的方式调用线程。
        /*MyThread myThread = new MyThread();
        myThread.setName("myThread");
        myThread.start(); //启动线程
        System.out.println("线程" + myThread.getClass().getSimpleName() + "启动了");
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main=" + Thread.currentThread().getName());
            }
        } catch (Exception e) {

        }*/

        //线程实际并不是按照start顺序来启动的
       /* MyThread1 t11 = new MyThread1(1);
        MyThread1 t12 = new MyThread1(2);
        MyThread1 t13 = new MyThread1(3);
        MyThread1 t14 = new MyThread1(4);
        MyThread1 t15 = new MyThread1(5);
        MyThread1 t16 = new MyThread1(6);
        t11.setName("A");
        t11.start();
        t12.setName("B");
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();*/

        //实现Runnable接口创建线程类。
        /*MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2, "myThread2"); //第二个参数为线程名称,这里意味着，可以把一个Thread对象的run方法交由其他的线程调用。
        thread.start();*/

        //创建了三个线程，线程之间不共享变量数据。
        /*MyThread3 a = new MyThread3("A");
        MyThread3 b = new MyThread3("B");
        MyThread3 c = new MyThread3("C");
        a.start();
        b.start();
        c.start();*/

        //多个线程共享变量，非线程安全
        //概念：非线程安全。指多个线程同时操作同一个变量，更改变量的值导致不同步，改变了程序执行流程，导致数据错乱。
        /*MyThread4 a = new MyThread4("A");
        MyThread4 b = new MyThread4("B");
        MyThread4 c = new MyThread4("C");
        MyThread4 d = new MyThread4("D");
        MyThread4 e = new MyThread4("E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();*/

        //多线程间，线程安全
        MyThread5 a = new MyThread5("A");
        MyThread5 b = new MyThread5("B");
        MyThread5 c = new MyThread5("C");
        MyThread5 d = new MyThread5("D");
        MyThread5 e = new MyThread5("E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            try {
                for (int i = 0; i < 10; i++) {
                    int time = (int) (Math.random() * 1000);
                    Thread.sleep(time);
                    System.out.println("run=" + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread运行结束");
        }
    }

    public static class MyThread1 extends Thread {

        private int i;

        public MyThread1(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "=" + Thread.currentThread().getName());
        }
    }

    //实现接口创建线程类，解决继承Thread实现的单继承问题。这里可以继承类，同时实现接口实现线程类。
    public static class MyThread2 implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    //实例变量和线程安全
    //线程之间不共享数据情况
    public static class MyThread3 extends Thread {

        private int count = 5;

        public MyThread3(String name) {
            super();
            this.setName(name); //设置线程名称
        }

        @Override
        public void run() {
            super.run();
            while (count > 0) {
                count--;
                System.out.println("由" + this.currentThread().getName() + "计算， count=" + count);
            }
        }
    }

    //线程间共享变量
    public static class MyThread4 extends Thread {

        private String pwd ="pwd"; //非静态变量，每个线程对象都拥有自己的实例，不会共享
        private int count_0 = 5;

        private static int count = 5; //静态变量，类对象共用，所以线程间共享该变量，多个线程会同时操作

        public MyThread4(String name) {
            super();
            this.setName(name); //设置线程名称
        }

        @Override
        public void run() {
            super.run();
            this.pwd = getName();
            System.out.println(this.currentThread().getName() + "=" + pwd);
            //这里不要使用for循环，因为使用同步同步后其他线程就得不到运行的机会了，一直由一个线程进行减法运算
            count--;
            System.out.println("由" + this.currentThread().getName() + "计算， count=" + count);
            count_0--;
            System.out.println("由" + this.currentThread().getName() + "计算， count_0=" + count_0);
        }
    }

    //改造MyThread4，线程安全，达到按顺序减1的结果。
    //在run方法前加上同步关键字synchronized,线程互斥调用run方法。
    //但是依然还会出现线程非安全的问题，是因为i--和println联合使用导致的另外一个问题。因为i--在println方法之前，虽然println方法是同步的，所以由非线程安全的问题。
    public static class MyThread5 extends Thread {

        private static int count = 5; //静态变量，类对象共用，所以线程间共享该变量，多个线程会同时操作

        public MyThread5(String name) {
            super();
            this.setName(name); //设置线程名称
        }

        @Override
        synchronized public void run() {
            super.run();
            //因为i--在println方法之前，虽然println方法是同步的，所以由非线程安全的问题。
//            count--;
//            System.out.println("由" + this.currentThread().getName() + "计算， count=" + count);
            //改造，这样，就是线程安全的了
            System.out.println("由" + this.currentThread().getName() + "计算， count=" + count--);
        }
    }
}
