package com.design.pattern.command.sample1;

/**
 * ��������������
 */
public class AddRequirementCommand extends Command {

    //ִ������һ�����������
    @Override
    public void execute() {
        //�ҵ�������
        super.rg.find();
        //����һ������
        super.rg.add();
        //��������ƻ�
        super.rg.plan();
    }
}
