package com.design.pattern.multition.sample02;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ���Ǳ����ˣ�һ���ʵ۶��ź򲻹����ˣ����ڻ������������ʵ�
 * TND�������ˣ��ҵ����ʵۣ���ͷ���밴�ͳ��ˣ�
 */
public class Minister {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int ministerNum =10; //10����
        for(int i=0;i<ministerNum;i++){
            Emperor emperor = Emperor.getInstance();
            System.out.print("��"+(i+1)+"���󳼲ΰݵ��ǣ�");
            emperor.emperorInfo();
        }
    }
}
