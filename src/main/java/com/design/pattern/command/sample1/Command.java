package com.design.pattern.command.sample1;

/**
 * ����ӿڡ�
 */
public abstract class Command {

    //�������鶼����ã��������ֱ��ʹ��
    protected RequirementGroup rg = new RequirementGroup(); //������
    protected PageGroup pg = new PageGroup(); //������
    protected CodeGroup cg = new CodeGroup(); //������;

    //ֻҪһ����������Ҫ����ʲô����
    public abstract void execute();
}
