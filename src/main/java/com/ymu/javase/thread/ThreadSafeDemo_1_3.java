package com.ymu.javase.thread;

//ģ��ѷ��̰߳�ȫ�������ĳ��̰߳�ȫ������
//������̰߳�ȫ�����⣬����Ҳ�Ǽ���ͬ���ؼ��֡��ö���̻߳�����ʹ��з�����������
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

        //�̷߳ǰ�ȫ�����Ϲؼ��֡����̵߳��û��⣬�ﵽ�̰߳�ȫ
        //����ͬ���ؼ��ֺ������̵߳��õ�ʱ���ǻ���ģ������õ��������ܷ��ʡ�
        //ȥ���ؼ������ԣ����ԣ������̲߳�ͬ���������
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
