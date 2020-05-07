package com.ymu.javase.thread;

//�߳�isAlive()����
//�жϵ�ǰ�߳��Ƿ��ڻ�Ծ״̬��
//�̻߳״̬��ָ���߳��Ѿ���������δ��ֹ���߳����������л���׼�����е�״̬��
public class ISAliveDemo_1_5 {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println("begin==" + myThread.isAlive());
        myThread.start();
        System.out.println("end==" + myThread.isAlive()); //�������������true��Ҳ������false��ȡ�����߳��Ƿ��Ѿ�������ɡ�
        Thread.sleep(5000);
        System.out.println("end1==" + myThread.isAlive()); //main�߳����ߺ�myThread�Ѿ�ִ����ɲ���ֹ�������������false
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
