package com.design.pattern.chainofresponsibility.sample1;

public class Father extends Handler {

    //����ֻ����Ů��������
    public Father(){
        super(1);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("--------Ů��������ʾ-------");
        System.out.println(women.getRequest());
        System.out.println("���׵Ĵ���:ͬ��\n");
    }
}
