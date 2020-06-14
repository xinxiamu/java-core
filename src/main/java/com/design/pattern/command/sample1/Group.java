package com.design.pattern.command.sample1;

/**
 * �����ߣ�����ɻ���ˡ���Ŀ�ֳ������顢UI����顢�����д�������飬ÿ���鶼Ҫ������ɾ�ĵ����
 */
public abstract class Group {

    //��Ҫ���ĸ������ۣ����ҵ��Ǹ��顣
    public abstract void find();

    //��Ҫ�����ӹ���
    public abstract void add();

    //��Ҫ��ɾ������
    public abstract void delete();

    //��Ҫ���޸Ĺ���
    public abstract void change();

    //��Ҫ��������еı���ƻ�
    public abstract void plan();

    @Override
    public String toString() {
        return super.toString();
    }
}
