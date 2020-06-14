package com.design.pattern.command.sample1;

/**
 * 接收者，具体干活的人。项目分成需求组、UI设计组、代码编写组三个组，每个组都要接受怎删改的命令。
 */
public abstract class Group {

    //你要和哪个组讨论，先找到那个组。
    public abstract void find();

    //被要求增加功能
    public abstract void add();

    //被要求删除功能
    public abstract void delete();

    //被要求修改功能
    public abstract void change();

    //被要求给出所有的变更计划
    public abstract void plan();

    @Override
    public String toString() {
        return super.toString();
    }
}
