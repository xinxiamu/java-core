package com.design.pattern.factory.method.demo1;

//客户端通过创建具体工厂对象来获取具体产品对象，而不是直接创建具体产品对象。这样就可以实现客户端与具体类的解耦，从而提高代码的可扩展性和可维护性。
public class Client {

    public static void main(String[] args) {
        ProductFactory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        productA.doSomething();

        ProductFactory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        productB.doSomething();
    }
}
