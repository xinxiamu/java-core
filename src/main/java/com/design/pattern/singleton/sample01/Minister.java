package com.design.pattern.singleton.sample01;

//大臣是天天要面见皇帝，今天见的皇帝和昨天的，前天不一样那就出问题了！
public class Minister {

    public static void main(String[] args) {
        //第一天
        Emperor emperor1=Emperor.getInstance();
        emperor1.emperorInfo(); //第一天见的皇帝叫什么名字呢？
        //第二天
        Emperor emperor2=Emperor.getInstance();
        Emperor.emperorInfo();
        //第三天
        Emperor emperor3=Emperor.getInstance();
        emperor2.emperorInfo();
        //三天见的皇帝都是同一个人，荣幸吧！
    }
}
