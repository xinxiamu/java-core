package com.ymu.javase.thread.stop;

import org.junit.Test;

/**t4
 * 
 * ����interrupt()���������ø÷������̲߳����������ֹ�ˣ���ֻ���ڵ�ǰ�̴߳��˸�ֹͣ�ı�ǡ�
 */
public class InterruptDemo_1 {

    public static void main(String[] args) {
        //������interrupt()���������ǲ�û��������ֹ�߳�
        /*try {
            MyThread myThread = new MyThread();
            myThread.setName("myThread");
            myThread.start();
            Thread.sleep(5000);
            myThread.interrupt(); //Ϊ�߳�myThread������ֹ��־�����ǲ�û��������ֹ�߳�
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main�߳��쳣");
        }*/

        //========================== �ж��߳��Ƿ��Ѿ��ж� start

        //�жϵ�ǰ�߳��Ƿ��Ѿ��ж�,interrupted()����
        /*try {
            MyThread myThread = new MyThread();
            myThread.setName("myThread");
            myThread.start();
            Thread.sleep(5000);
            myThread.interrupt();
            //��ǰ�߳���main�̣߳���ȷ��û�жϣ����Զ����false
            System.out.println("�Ƿ�ֹͣ 1��=" + myThread.interrupted());
            System.out.println("�Ƿ�ֹͣ 2��=" + myThread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main�߳��쳣");
        }
        System.out.println("end��");*/

        //�жϵ�ǰ�߳��Ƿ��Ѿ��ж�,interrupted()���������ú������ж�״̬��
        /*try {
            Thread.currentThread().interrupt();
            //��ǰ�߳���main�߳��Ѿ��ж�,ֻ�Ǵ����жϱ�־��ʵ����main�̻߳��������еġ�
            System.out.println("�Ƿ�ֹͣ 1��=" + Thread.interrupted()); //���true���Ѿ���־�ж�
            System.out.println("�Ƿ�ֹͣ 2��=" + Thread.interrupted()); //���false����Ϊ��һ�ε��ú������ж�״̬�������´ε��÷���false��
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("main�߳��쳣");
        }
        System.out.println("end��");*/

        //�жϸ��߳��Ƿ��Ѿ��жϡ�isInterrupted(),�жϵ��Ǹ��߳��Ƿ��Ѿ��жϣ��������״̬��־�����ǵ�ǰ�̡߳�ע�����������
        try {
            MyThread myThread = new MyThread();
            myThread.setName("myThread");
            myThread.start();
//            Thread.sleep(5000);
            myThread.interrupt();
            System.out.println("�Ƿ�ֹͣ 1��=====================" + myThread.isInterrupted());
            System.out.println("�Ƿ�ֹͣ 2��=====================" + myThread.isInterrupted());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("main�߳��쳣");
        }
        System.out.println("end��");

        //========================== �ж��߳��Ƿ��Ѿ��ж� end
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
