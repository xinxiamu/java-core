package com.design.pattern.factory.method.demo1;

// 具体工厂A
class ConcreteFactoryA implements ProductFactory {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}
