package com.design.pattern.factory.method.demo1;

// 具体工厂A
class ConcreteFactoryB implements ProductFactory {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}
