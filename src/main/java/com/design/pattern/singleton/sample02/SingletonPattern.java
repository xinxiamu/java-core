package com.design.pattern.singleton.sample02;

//通用单例模式
//线程安全
public class SingletonPattern {

    private static SingletonPattern singletonPattern = new SingletonPattern();

    private SingletonPattern() {}

    //添加同步关键字，线程安全。
    public synchronized static SingletonPattern getInstance() {
        return singletonPattern;
    }
}
