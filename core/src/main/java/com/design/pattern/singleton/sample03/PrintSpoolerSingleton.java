package com.design.pattern.singleton.sample03;

//非线程安全
//不推荐使用
public class PrintSpoolerSingleton {
    private static PrintSpoolerSingleton instance = null;

    private PrintSpoolerSingleton() {
    }

    public static PrintSpoolerSingleton getInstance() throws PrintSpoolerException {
        if (instance == null) {
            System.out.println("创建打印池！");
            instance = new PrintSpoolerSingleton();
        } else {
            throw new PrintSpoolerException("打印池正在工作中！");
        }
        return instance;
    }

    public void manageJobs() {
        System.out.println("管理打印任务！");
    }
}

class Run {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                PrintSpoolerSingleton instance = null;
                try {
                    instance = PrintSpoolerSingleton.getInstance();
                } catch (PrintSpoolerException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " : " + instance.hashCode());
            }).start();
        }
    }
}
