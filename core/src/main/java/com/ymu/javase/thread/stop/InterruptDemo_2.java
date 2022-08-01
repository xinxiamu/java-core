package com.ymu.javase.thread.stop;

import org.junit.Test;

/**
 *
 * 介绍interrupt()方法。调用该方法，线程并不是真的终止了，而只是在当前线程打了个停止的标记。
 */
public class InterruptDemo_2 {

    public static void main(String[] args) {
        //调用了interrupt()方法，但是并没有真正终止线程
        /*try {
            MyThread myThread = new MyThread();
            myThread.setName("myThread");
            myThread.start();
            Thread.sleep(5000);
            myThread.interrupt(); //为线程myThread打上终止标志，但是并没有真正终止线程
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main线程异常");
        }*/

        //========================== 判断线程是否已经中断 start

        //判断当前线程是否已经中断,interrupted()方法
        /*try {
            MyThread myThread = new MyThread();
            myThread.setName("myThread");
            myThread.start();
            Thread.sleep(5000);
            myThread.interrupt();
            //当前线程是main线程，的确还没中断，所以都输出false
            System.out.println("是否停止 1？=" + myThread.interrupted());
            System.out.println("是否停止 2？=" + myThread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main线程异常");
        }
        System.out.println("end！");*/

        //判断当前线程是否已经中断,interrupted()方法，调用后会清除中断状态。
        /*try {
            Thread.currentThread().interrupt();
            //当前线程是main线程已经中断,只是打上中断标志，实际上main线程还是在运行的。
            System.out.println("是否停止 1？=" + Thread.interrupted()); //输出true，已经标志中断
            System.out.println("是否停止 2？=" + Thread.interrupted()); //输出false，因为第一次调用后会清楚中断状态，所以下次调用返回false。
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("main线程异常");
        }
        System.out.println("end！");*/

        //判断该线程是否已经中断。isInterrupted(),判断的是该线程是否已经中断，但不清除状态标志，不是当前线程。注意和上面区别。
        try {
            MyThread myThread = new MyThread();
            myThread.setName("myThread");
            myThread.start();
//            Thread.sleep(5000);
            myThread.interrupt();
            System.out.println("是否停止 1？=====================" + myThread.isInterrupted());
            System.out.println("是否停止 2？=====================" + myThread.isInterrupted());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("main线程异常");
        }
        System.out.println("end！");

        //========================== 判断线程是否已经中断 end
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 500000; i++) {
                System.out.println(this.getName() + ":i=" + (i + 1));
            }
        }
    }
}
