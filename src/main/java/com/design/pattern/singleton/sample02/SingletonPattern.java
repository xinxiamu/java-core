package com.design.pattern.singleton.sample02;

//通用单例模式，急加载
//线程安全
public class SingletonPattern {

    private static SingletonPattern singletonPattern = new SingletonPattern(); //马上初始化，急加载

    private SingletonPattern() {}

    public static SingletonPattern getInstance() {
        return singletonPattern;
    }
}
