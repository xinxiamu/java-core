package com.design.pattern.chainofresponsibility.sample1;

public class Son extends Handler {

    //����ֻ����ĸ�׵�����
    public Son(){
        super(3);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("--------ĸ���������ʾ-------");
        System.out.println(women.getRequest());
        System.out.println("���ӵĴ��ǣ�ͬ��\n");
    }
}
