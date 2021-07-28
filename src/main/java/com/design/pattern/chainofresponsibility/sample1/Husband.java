package com.design.pattern.chainofresponsibility.sample1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * 儿子类
 */
public class Husband extends Handler {

    //丈夫只处理妻子的请求
    public Husband(){
        super(2);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("--------妻子向丈夫请示-------");
        System.out.println(women.getRequest());
        System.out.println("丈夫的答复是：同意\n");
    }
}
