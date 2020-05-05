package com.ymu.javase.thread;

//模拟把非线程安全环境，改成线程安全环境。
//解决非线程安全的问题，这里也是加上同步关键字。让多个线程互斥访问共有方法，变量。
public class ThreadSafeDemo_1_3 {

    public static void main(String[] args) {
        ALogin aLogin = new ALogin();
        aLogin.setName("aLogin");
        aLogin.start();
        BLogin bLogin = new BLogin();
        bLogin.setName("bLogin");
        bLogin.start();
    }

    public static class LoginServlet {
        private static String usernameRef;
        private static String passwordRef;

        //线程非安全，加上关键字。让线程调用互斥，达到线程安全
        //加上同步关键字后，这里线程调用的时候是互斥的，必须拿到锁，才能访问。
        //去掉关键字试试，可以，看到线程不同步的情况。
        synchronized public static void doPost(String username, String password) {
            try {
                usernameRef = username;

                if (username.equals("a")) {
                    Thread.sleep(5000);
                }

                passwordRef = password;

                System.out.println(Thread.currentThread().getName() + ":" + "username=" + usernameRef + " password=" + passwordRef);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ALogin extends Thread {

        @Override
        public void run() {
            LoginServlet.doPost("a", "aa");
        }
    }

    public static class BLogin extends Thread {

        @Override
        public void run() {
            LoginServlet.doPost("b", "bb");
        }
    }
}
