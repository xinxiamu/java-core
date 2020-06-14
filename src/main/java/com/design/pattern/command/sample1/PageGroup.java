package com.design.pattern.command.sample1;

/**
 * �������ְ������Ƴ�һ��Ư�����򵥡���ݵĽ���
 */
public class PageGroup extends Group {

    @Override
    public void find() {
        System.out.println("�ҵ�������...");
    }

    @Override
    public void add() {
        System.out.println("�ͻ�Ҫ������һ��ҳ��...");
    }

    @Override
    public void delete() {
        System.out.println("�ͻ�Ҫ��ɾ��һ��ҳ��...");
    }

    @Override
    public void change() {
        System.out.println("�ͻ�Ҫ���޸�һ��ҳ��...");
    }

    @Override
    public void plan() {
        System.out.println("�ͻ�Ҫ��ҳ�����ƻ�...");
    }
}
