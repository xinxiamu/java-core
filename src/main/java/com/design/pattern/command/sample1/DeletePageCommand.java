package com.design.pattern.command.sample1;

/**
 * �������ɾ��һ��ҳ�������
 */
public class DeletePageCommand extends Command {

    @Override
    public void execute() {
        pg.find();
        pg.delete();
        pg.plan();
    }
}
