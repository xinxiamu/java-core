package com.design.pattern.command.sample1;

/**
 * �������ְ����ʵ��ҵ���߼�����Ȼ�������ݿ������
 */
public class CodeGroup extends Group {

    @Override
    public void find() {
        System.out.println("�ҵ�������...");
    }

    @Override
    public void add() {
        System.out.println("�ͻ�Ҫ������һ���...");
    }

    @Override
    public void delete() {
        System.out.println("�ͻ�Ҫ��ɾ��һ���...");
    }

    @Override
    public void change() {
        System.out.println("�ͻ�Ҫ���޸�һ���...");
    }

    @Override
    public void plan() {
        System.out.println("�ͻ�Ҫ��������ƻ�...");
    }
}
