package com.design.pattern.chainofresponsibility.sample1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * 古代女性的总称
 */
public class Women implements IWomen {

    /*
     * 通过一个int类型的参数来描述妇女的个人状况
     * 1---未出嫁
     * 2---出嫁
     * 3---夫死
     */
    private int type = 0;

    //妇女的请示
    private String request = "";

    //构造函数传递过来请求
    public Women(int _type, String _request) {
        this.type = _type;
        //为了显示好看点，我在这里做了点处理
        switch (this.type) {
            case 1:
                this.request = "女儿的请求是：" + _request;
                break;
            case 2:
                this.request = "妻子的请求是：" + _request;
                break;
            case 3:
                this.request = "母亲的请求是：" + _request;
        }
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
