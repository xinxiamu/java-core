package com.ymu.javase.thread;

//yield方法
//线程放弃当前的CPU资源，让给其它的此线程任务占用cpu执行事件。
//放弃时间不确定，可能随时又获取cpu时间片执行
public class ThreadYieldDemo_1_9 {
    public static void main(String[] args) {
        String tag = "1_2";
        System.out.println(tag.substring(tag.indexOf("_") + 1));
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        Long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 500000000; i++) {
            Thread.yield(); //放弃占用cpu时间片资源
            count++;
        }
    }
}
