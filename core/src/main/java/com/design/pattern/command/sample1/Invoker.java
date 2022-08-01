package com.design.pattern.command.sample1;

/**
 * 调用者。相当于项目经理。接受命令，执行并转发具体接收者干活。
 */
public class Invoker {

    //什么命令
    private Command command;

    //客户发出命令
    public void setCommand(Command command){
        this.command = command;
    }

    //执行客户的命令
    public void action(){
        this.command.execute();
    }
}
