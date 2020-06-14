package com.design.pattern.command.sample1;

/**
 * �ͻ����Ǽ׷���������Ǯ��һ�������ϴ�
 */
public class Client1 {

    public static void main(String[] args) {
        //�������ǵĽ�ͷ��
        Invoker xiaoSan = new Invoker(); //��ͷ�˾�����С��

        //�ͻ�Ҫ������һ������
        System.out.println("-------------�ͻ�Ҫ������һ������-----------------");
        //�ͻ���������������
        Command command = new AddRequirementCommand();

        //��ͷ�˽��յ�����
        xiaoSan.setCommand(command);

        //��ͷ��ִ������
        xiaoSan.action();
    }
}
