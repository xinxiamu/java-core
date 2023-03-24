package com.design.pattern.abstract_factory.demo1;

public class ConcreteProductA implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProductA created.");
    }
}
