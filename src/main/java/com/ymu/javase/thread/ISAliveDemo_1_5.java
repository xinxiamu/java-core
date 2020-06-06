package com.ymu.javase.thread;

//�߳�isAlive()����
//�жϵ�ǰ�߳��Ƿ��ڻ�Ծ״̬��
//�̻߳״̬��ָ���߳��Ѿ���������δ��ֹ���߳����������л���׼�����е�״̬��
public class ISAliveDemo_1_5 {

    public static void main(String[] args) throws InterruptedException {
       /* MyThread myThread = new MyThread();
        System.out.println("begin==" + myThread.isAlive());
        myThread.start();
        System.out.println("end==" + myThread.isAlive()); //�������������true��Ҳ������false��ȡ�����߳��Ƿ��Ѿ�������ɡ�
        Thread.sleep(5000);
        System.out.println("end1==" + myThread.isAlive()); //main�߳����ߺ�myThread�Ѿ�ִ����ɲ���ֹ�������������false*/

        CountOperate countOperate = new CountOperate();
        Thread t1 = new Thread(countOperate);
        System.out.println("main begin t1 isAlive=" + t1.isAlive());
        t1.setName("A");
        t1.start();
        System.out.println("main end t1 isAlive=" + t1.isAlive());
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

    public static class CountOperate extends Thread {

        public CountOperate() {
            System.out.println("CountOperate---------begin");
            System.out.println("Thread.currentThread.getName()=" + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
            System.out.println("this.getName()=" + this.getName());
            System.out.println("this.isAlive()=" + this.isAlive());
            System.out.println("CountOperate---------end");
        }

        @Override
        public void run() {
            System.out.println("run::::::::::begin");
            System.out.println("Thread.currentThread.getName()=" + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
            System.out.println("this.getName()=" + this.getName());
            System.out.println("this.isAlive()=" + this.isAlive());
            System.out.println("run:::::::::::end");
        }
    }
}
