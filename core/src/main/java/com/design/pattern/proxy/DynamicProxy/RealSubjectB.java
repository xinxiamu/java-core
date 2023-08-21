package com.design.pattern.proxy.DynamicProxy;

public class RealSubjectB implements AbstractSubject {

    @Override
    public void request() {
        System.out.println("真实主题类B！");
    }
}
