package com.design.pattern.command.sample1;

/**
 * 具体命令。删除一个页面的命令
 */
public class DeletePageCommand extends Command {

    @Override
    public void execute() {
        pg.find();
        pg.delete();
        pg.plan();
    }
}
