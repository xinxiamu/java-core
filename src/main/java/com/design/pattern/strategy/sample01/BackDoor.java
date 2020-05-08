package com.design.pattern.strategy.sample01;

/**
 * 策略之一。找乔国老帮忙，使孙权不能杀刘备
 */
public class BackDoor implements IStrategy {

    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
