package com.design.pattern.decorator.sample01;

/**
 * 学校发的成绩单
 */
public interface SchoolReport {

    //成绩单的主要展示的就是你的成绩情况
    void report();

    //成绩单要家长签字，这个是最要命的
    void sign(String name);
}
