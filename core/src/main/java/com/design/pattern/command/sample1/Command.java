package com.design.pattern.command.sample1;

/**
 * 命令接口。
 */
public abstract class Command {

    //把三个组都定义好，子类可以直接使用
    protected RequirementGroup rg = new RequirementGroup(); //需求组
    protected PageGroup pg = new PageGroup(); //美工组
    protected CodeGroup cg = new CodeGroup(); //代码组;

    //只要一个方法，你要我做什么事情
    public abstract void execute();
}
