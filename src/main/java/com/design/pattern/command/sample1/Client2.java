package com.design.pattern.command.sample1;

public class Client2 {

    public static void main(String[] args) {
        //�������ǵĽ�ͷ��
        Invoker xiaoSan = new Invoker(); //��ͷ�˾�����С��

        //�ͻ�Ҫ������һ������
        System.out.println("-------------�ͻ�Ҫ��ɾ��һ��ҳ��-----------------");
        //�ͻ���������������
        //Command command = new AddRequirementCommand();
        Command command = new DeletePageCommand();

        //��ͷ�˽��յ�����
        xiaoSan.setCommand(command);

        //��ͷ��ִ������
        xiaoSan.action();
    }
}
