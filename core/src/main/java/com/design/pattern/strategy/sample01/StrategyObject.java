package com.design.pattern.strategy.sample01;

public enum StrategyObject {
    //对应每个具体策略名称
    BackDoor("com.design.pattern.strategy.sample01.BackDoor"),
    BlockEnemy(""),
    GivenGreenLight("");

    private String clazzName;

    StrategyObject(String clazzName) {
        this.clazzName = clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getClazzName() {
        return clazzName;
    }

    /**
     * 获取具体策略对象。
     * @param objectEnum
     * @return
     */
    public static Object getStrategy(StrategyObject objectEnum) {
        if (objectEnum == null) {
            throw new NullPointerException();
        }

        try {
            Class c = Class.forName(objectEnum.getClazzName());
            Object obj = c.getDeclaredConstructor().newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
