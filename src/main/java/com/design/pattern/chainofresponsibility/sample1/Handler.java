package com.design.pattern.chainofresponsibility.sample1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ��ϵ��ᣬ�Ǿ�������������Ȩ����handler����Ȩ
 */
public abstract class Handler {

    //�ܴ���ļ���
    private int level =0;

    //���δ��ݣ���һ������������˭
    private Handler nextHandler;

    //ÿ���඼Ҫ˵��һ���Լ��ܴ�����Щ����
    public Handler(int _level){
        this.level = _level;
    }

    //һ��Ů�ԣ�Ů�������ӻ�����ĸ�ף�Ҫ���֣���Ҫ�����������
    public final void HandleMessage(IWomen women) {
        if (women.getType() == this.level) {
            this.response(women);
        } else {
            if (this.nextHandler != null) { //�к������ڣ��Ű������������
                this.nextHandler.HandleMessage(women);
            } else { //�Ѿ�û�к����������ˣ����ô�����
                System.out.println("-----------û�ط���ʾ�ˣ���������---------\n");
            }
        }
    }

    /*
     * ����������㴦��ķ��أ���Ӧ����������һ�����ڵ��ˣ�����
     * Ů�������ˣ���������ʾ�Ƿ���Թ�֣��Ǹ��׾�Ӧ�ø���Ů����Ӧ�����ɷ���ʾ
     */
    public void setNext(Handler _handler){
        this.nextHandler = _handler;
    }

    //����ʾ�ǵ�ȻҪ��Ӧ
    public abstract void response(IWomen women);
}
