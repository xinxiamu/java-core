package com.design.pattern.abstract_factory.demo1;

public class Demo1 {

    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        Product productA = factory.createProduct("A");
        Product productB = factory.createProduct("B");
        Product productC = factory.createProduct("C");

        productA.operation();
        productB.operation();
        productC.operation();
    }
}
