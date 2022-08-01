package com.design.pattern.factory.method;

//定义具体人类，黄种人
public class YellowHuman implements Human {
    @Override
    public void talk() {
        System.out.println("黄种人会说话……");
    }

    @Override
    public void cry() {
        System.out.println("黄种人会哭……");
    }
}
