package com.ymu.javase.thread.stop;

//异常法停止线程
//总结：推荐异常中断线程的方式
//这样，线程的异常可以往上面抛出，使得线程停止的事件得以传播
public class InterruptByError {

    public static void main(String[] args) {
        try {
            /*//执行2s后，中断线程
            MyThread t = new MyThread();
            t.start();
            Thread.sleep(2000);
            t.interrupt();*/

            //执行2s后，中断线程
            /*MyThread1 t = new MyThread1();
            t.start();
            Thread.sleep(2000);
            t.interrupt();
            System.out.println(">>>>>t线程中断停止，但是main线程继续");*/

            //中断挂起的线程
            /*MyThread2 t2 = new MyThread2();
            t2.start();
            Thread.sleep(200);
            t2.interrupt();*/

            //暴力中断，不推荐
            //强制停止，可能导致线程引用的一些资源无法得到释放。
            //对锁定的对象突然释放，会造成数据不一致。
            /*MyThread3 t3 = new MyThread3();
            t3.start();
            Thread.sleep(8000);
            t3.stop();*/

            //通过return返回中断线程
            MyThread4 t4 = new MyThread4();
            t4.start();
            Thread.sleep(2000);
            t4.interrupt();
        } catch (Exception e) {
            System.out.println("主线程调用异常");
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5000000; i++) {
            System.out.println(">>>>>i = " + i);
            if (this.isInterrupted()) {
                System.out.println(">>>>>线程是停止状态，我要退出了！");
                break;
            }
        }
        System.out.println(">>>>>>>我被输出，下面代码会继续执行哦，并没有真正跳出run方法");
    }
}

//通过抛出异常中断线程
class MyThread1 extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 5000000; i++) {
                System.out.println(">>>>>i = " + i);
                if (this.isInterrupted()) {
                    System.out.println(">>>>>线程是停止状态，我要退出了！");
                    throw new InterruptedException(""); //通过抛出异，跳出run方法，不会继续执行for循环后面代码,达到停止线程的目的
                }
            }
            System.out.println(">>>>>>>中断后，我不会再被执行输出哦");
        } catch (Exception e) {
            System.err.println(">>>>>>MyThread1.run抛出异常了");
            e.printStackTrace();
        }
    }
}

class MyThread2 extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 100000; i++) {
                System.out.println(">>>>>i = " + (i + 1));
            }
            System.out.println(">>>>线程开始执行");
            Thread.sleep(200000); //线程挂起，当其它线程中断该异常的时候，将会抛出异常
            System.out.println(">>>>>>>线程执行结束"); //中断了，这句无法执行
        } catch (Exception e) {
            System.err.println(">>>>>>线程挂起，被中断，抛出异常");
            e.printStackTrace();
        }
    }
}

class MyThread3 extends Thread {

    private int i = 0;

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                i++;
                System.out.println("i = " + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println(">>>>>>stop 暴力中断");
            e.printStackTrace();
        }
    }
}

class MyThread4 extends Thread {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(">>>>>线程已经标志中断");
                return; //返回，退出run方法，线程执行结束
            }
        }
    }
}
