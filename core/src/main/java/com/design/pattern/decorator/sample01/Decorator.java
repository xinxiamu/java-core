package com.design.pattern.decorator.sample01;

/**
 * 成绩单装饰抽象类
 */
public abstract class Decorator implements SchoolReport {

    //首先我要知道是那个成绩单
    private SchoolReport sr;

    //构造函数，传递成绩单过来
    public Decorator(SchoolReport sr) {
        this.sr = sr;
    }

    //成绩单还是要被看到的

    @Override
    public void report() {
        this.sr.report();
    }

    //看完成绩单家长要签名
    @Override
    public void sign(String name) {
        this.sr.sign(name);
    }
}
