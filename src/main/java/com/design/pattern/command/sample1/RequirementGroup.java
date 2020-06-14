package com.design.pattern.command.sample1;

/**
 * �������ְ���ǺͿͻ�̸��������������Ӧ�ö���ҵ������ר��
 */
public class RequirementGroup extends Group {

    @Override
    public void find() {
        System.out.println("�ҵ������顭��");
    }

    @Override
    public void add() {
        System.out.println("�ͻ�Ҫ������һ�����󡭡�");
    }

    @Override
    public void delete() {
        System.out.println("�ͻ�Ҫ���޸�һ�����󡭡�");
    }

    @Override
    public void change() {
        System.out.println("�ͻ�Ҫ��ɾ��һ�����󡭡�");
    }

    @Override
    public void plan() {
        System.out.println("�ͻ�Ҫ���������ƻ�����");
    }
}
