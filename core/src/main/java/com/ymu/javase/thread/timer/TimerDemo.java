package com.ymu.javase.thread.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//用timer实现定时任务
//设定时间在当前时间之前，马上执行任务
//设定时间在当前时间之后，延迟执行任务
public class TimerDemo {

    public static void main(String[] args) throws ParseException {
        //查看源码构造函数，可以看到Timer有两个构造函数，一个是无参构造函数，一个是带参构造函数。
        //默认是创建一个线程并启动一直运行，也可以通过参数设置成守护进程。
        //设置成守护进程后，程序退出时，守护进程会自动关闭。
        Timer timer = new Timer();

        MyTimerTask task = new MyTimerTask();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String dateString = "2023-11-27 15:35:00";
        String dateString = "2023-11-27 15:44:00";
        Date dateRef = df.parse(dateString);

        System.out.println("设定任务执行时间：" + dateRef);
        System.out.println("当前时间：" + new Date());

        //schedule方法，第一个参数是任务，第二个参数是执行时间。
        timer.schedule(task, dateRef);
    }

    static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务执行了! 时间：" + new Date());
        }
    }
}

//演示多个任务
class Run2 {
    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        MyTimerTask1 task1 = new MyTimerTask1();
        MyTimerTask2 task2 = new MyTimerTask2();

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = "2023-11-28 16:41:00";

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString2 = "2023-11-28 16:41:10";

        //任务一还没执行完，但是任务二的设定时间虽然到了，但是必须等待任务一执行完毕后才能执行。延迟执行了
        //因为任务是一个队列，一个一个执行的。
        timer.schedule(task1, df1.parse(dateString1));
        timer.schedule(task2, df2.parse(dateString2));

        //输出结果：
        //任务1执行了! 开始时间：Tue Nov 28 16:41:00 CST 2023
        //任务1执行了! 结束时间：Tue Nov 28 16:41:20 CST 2023
        //任务2执行了! 时间：Tue Nov 28 16:41:20 CST 2023
    }

    static class MyTimerTask1 extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务1执行了! 开始时间：" + new Date());
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务1执行了! 结束时间：" + new Date());
        }
    }

    static class MyTimerTask2 extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务2执行了! 时间：" + new Date());
        }
    }
}

class Run3 {

    private static final Timer timer = new Timer();
    public static void main(String[] args) throws ParseException {
        MyTimerTask1 task1 = new MyTimerTask1();
        MyTimerTask2 task2 = new MyTimerTask2();

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = "2023-11-28 16:54:00";

        timer.schedule(task1, df1.parse(dateString1), 4000); //每隔4秒执行一次，无限循环执行
        timer.schedule(task2, df1.parse(dateString1), 4000); //每隔4秒执行一次，无限循环执行

        //timer.schedule(task1, 7000); //延迟7秒执行一次
        //timer.schedule(task1, 3000, 4000); //延迟3秒后执行，然后每隔4秒执行一次
    }

    static class MyTimerTask1 extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务1执行了! 开始时间：" + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务1执行了! 结束时间：" + new Date());
        }
    }

    static class MyTimerTask2 extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务2执行了! 开始时间：" + new Date());
            try {
                Thread.sleep(2000);
                this.cancel(); //取消任务，把该任务从任务队列中移除
                System.out.println("任务2已经已从任务队列移除，不再执行");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2执行了! 结束时间：" + new Date());

            timer.cancel(); //timer的cancel方法是从任务队列中移除所有的任务。不一定保证马上能获取到队列锁
            System.out.println("timer调用cancel方法，移除任务队列中所有的任务了，所有任务停止执行");
        }
    }
}
