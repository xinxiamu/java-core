package com.design.pattern.command.sample1;

/**
 * �����ߡ��൱����Ŀ�����������ִ�в�ת����������߸ɻ
 */
public class Invoker {

    //ʲô����
    private Command command;

    //�ͻ���������
    public void setCommand(Command command){
        this.command = command;
    }

    //ִ�пͻ�������
    public void action(){
        this.command.execute();
    }
}
