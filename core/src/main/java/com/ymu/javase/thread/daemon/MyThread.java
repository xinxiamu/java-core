package com.ymu.javase.thread.daemon;

public class MyThread extends Thread {
    private int i;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i=" + i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
