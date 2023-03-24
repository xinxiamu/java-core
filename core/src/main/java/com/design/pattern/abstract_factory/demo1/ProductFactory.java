package com.design.pattern.abstract_factory.demo1;

public class ProductFactory {

    /**
     * 创建产品。根据类型创建不同产品对象
     * @param type
     * @return
     */
    public Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else if (type.equals("B")) {
            return new ConcreteProductB();
        } else if (type.equals("C")) {
            return new ConcreteProductC();
        } else {
            throw new IllegalArgumentException("Invalid type: " + type);
        }
    }
}
