package com.design.pattern.factory.method;

//定义具体人类，白种人
public class WhiteHuman implements Human {
    @Override
    public void talk() {
        System.out.println("白种人会说话……");
    }

    @Override
    public void cry() {
        System.out.println("白种人会哭……");
    }
}
