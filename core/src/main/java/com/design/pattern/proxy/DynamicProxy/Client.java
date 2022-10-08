package com.design.pattern.proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk原生动态代理。
 */
public class Client {
    public static void main(String[] args) {
        InvocationHandler handler = null;
        AbstractSubject subject = null;

        handler = new DynamicProxy(new RealSubjectA());
        subject = (AbstractSubject) Proxy.newProxyInstance(AbstractSubject.class.getClassLoader(), new Class[]{AbstractSubject.class}, handler); //面向接口进行代理
        subject.request();

        System.out.println("------------------------------");

        handler = new DynamicProxy(new RealSubjectB());
        subject = (AbstractSubject) Proxy.newProxyInstance(AbstractSubject.class.getClassLoader(), new Class[]{AbstractSubject.class}, handler);
        subject.request();
    }
}
