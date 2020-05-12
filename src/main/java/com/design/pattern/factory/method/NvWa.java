package com.design.pattern.factory.method;

//��˵�е�Ů洣�������������
public class NvWa {

    public static void main(String[] args) {
        //Ů洵�һ�����ˣ��������ʣ�����㣬����㣬ȱ�ݲ�Ʒ
        System.out.println("------------����ĵ�һ�����������ģ�����-----------------");
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.talk();

        //Ů洵ڶ������ˣ�������㣬Ȼ���ֳ��˸���Ʒ������
        System.out.println("\n\n------------����ĵڶ������������ģ�����-----------------");
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.talk();

        //���������ˣ���λ�����յ����ã���ɫ���ࣨ��д���ˣ�����������壩����ע��RB�˲����ڴ���
        System.out.println("\n\n------------����ĵ��������������ģ���ɫ����-----------------");
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.cry();
        yellowHuman.talk();
    }
}
