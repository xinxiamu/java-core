package com.ymu.javase.thread.threadsafe;

//synchronized同步方法
//演示方法内部变量是线程安全的
public class MethodSafeDemo {

    public void add(String username) {
        try {
            int mun = 0;
            if ("a".equals(username)) {

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
