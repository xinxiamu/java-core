package com.design.pattern.chainofresponsibility.sample1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ������
 */
public class Husband extends Handler {

    //�ɷ�ֻ�������ӵ�����
    public Husband(){
        super(2);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("--------�������ɷ���ʾ-------");
        System.out.println(women.getRequest());
        System.out.println("�ɷ�Ĵ��ǣ�ͬ��\n");
    }
}
