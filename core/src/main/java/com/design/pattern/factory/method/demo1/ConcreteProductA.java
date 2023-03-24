package com.design.pattern.factory.method.demo1;

public class ConcreteProductA implements Product {

    @Override
    public void doSomething() {
        System.out.println("ConcreteProductA doSomething");
    }
}
