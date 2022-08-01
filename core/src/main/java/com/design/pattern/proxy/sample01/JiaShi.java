package com.design.pattern.proxy.sample01;

/**
 * 卢俊义的老婆贾氏（就是和那个固管家苟合的那个），和潘金莲一类女人。
 */
public class JiaShi implements KindWomen {

    @Override
    public void makeEyesWithMan() {
        System.out.println("贾氏抛媚眼");
    }

    @Override
    public void happyWithMan() {
        System.out.println("贾氏正在Happy中......");
    }
}
