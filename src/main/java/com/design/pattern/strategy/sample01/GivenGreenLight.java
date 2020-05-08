package com.design.pattern.strategy.sample01;

/**
 * 策略之二。求吴国太开个绿灯
 */
public class GivenGreenLight implements IStrategy {

    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯,放行！");
    }
}
