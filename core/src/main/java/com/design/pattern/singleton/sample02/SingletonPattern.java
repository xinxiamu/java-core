package com.design.pattern.singleton.sample02;

//通用单例模式，饿汉式（建议使用）
//线程安全
//缺点，没有lazy loading的效果，从而降低内存的使用率。
public class SingletonPattern {

    private static SingletonPattern singletonPattern = new SingletonPattern(); //马上初始化，急加载

    private SingletonPattern() {}

    public static SingletonPattern getInstance() {
        return singletonPattern;
    }
}

class Run {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                SingletonPattern instance = SingletonPattern.getInstance();
                System.out.println(Thread.currentThread().getName() + " : " + instance.hashCode());
            }).start();
        }
    }
}
