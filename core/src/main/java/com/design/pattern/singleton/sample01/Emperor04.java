package com.design.pattern.singleton.sample01;

/**
 * 线程安全。最终建议使用的。
 *  <br>
 *     优点：达到了lazy loading的效果，即按需创建实例。
 */
public final class Emperor04 {

    private Emperor04() {
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }


    public static Emperor04 getInstance() {
        return Emperor04Holder.INSTANCE;
    }

    private static class Emperor04Holder {
        private static final Emperor04 INSTANCE = new Emperor04();
    }
}
