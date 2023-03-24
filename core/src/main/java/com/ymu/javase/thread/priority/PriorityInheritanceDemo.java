package com.ymu.javase.thread.priority;

//线程优先级的继承性
public class PriorityInheritanceDemo {

    public static void main(String[] args) {
        System.out.println("main线程优先级：" + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(8); //主线程优先级设置
        Thread1 thread1 = new Thread1();
//        thread1.setPriority(6);
        thread1.start();
    }

    static class Thread1 extends Thread {

        @Override
        public void run() {
            System.out.println("我是线程Thread1，我的优先级是：" + this.getPriority());
            //创建启动线程Thread2
            Thread2 thread2 = new Thread2();
            thread2.start();
        }
    }
    static class Thread2 extends Thread {

        @Override
        public void run() {
            System.out.println("我是线程Thread，我的优先级是：" + this.getPriority());
        }
    }

}
