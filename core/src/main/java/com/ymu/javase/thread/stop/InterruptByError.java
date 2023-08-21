package com.ymu.javase.thread.stop;

//异常法停止线程
public class InterruptByError {

    public static void main(String[] args) {
        try {
            /*//执行2s后，中断线程
            MyThread t = new MyThread();
            t.start();
            Thread.sleep(2000);
            t.interrupt();*/

            //执行2s后，中断线程
            MyThread1 t = new MyThread1();
            t.start();
            Thread.sleep(2000);
            t.interrupt();
            System.out.println(">>>>>t线程中断停止，但是main线程继续");
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
