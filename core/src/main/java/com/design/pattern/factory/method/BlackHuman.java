package com.design.pattern.factory.method;

//定义具体人类，黑种人
public class BlackHuman implements Human {
    @Override
    public void talk() {
        System.out.println("黑种人会说话……");
    }

    @Override
    public void cry() {
        System.out.println("黑种人会哭……");
    }
}
