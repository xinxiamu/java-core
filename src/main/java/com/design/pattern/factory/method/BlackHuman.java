package com.design.pattern.factory.method;

//����������࣬������
public class BlackHuman implements Human {
    @Override
    public void talk() {
        System.out.println("�����˻�˵������");
    }

    @Override
    public void cry() {
        System.out.println("�����˻�ޡ���");
    }
}
