package com.design.pattern.factory.method;

//����������࣬������
public class YellowHuman implements Human {
    @Override
    public void talk() {
        System.out.println("�����˻�˵������");
    }

    @Override
    public void cry() {
        System.out.println("�����˻�ޡ���");
    }
}
