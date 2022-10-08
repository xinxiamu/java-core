package com.design.pattern.proxy.cglib;

import com.design.pattern.proxy.User;

public class UserCglibServiceImpl implements UserServiceCglib {

    //加了final修饰符的方法将不会被覆盖，无法代理
    /*@Override
    public final void addUser(User user) {
        System.out.println("cglib动态代理...正在注册用户，用户信息为：" + user);
    }*/

    public void addUser(User user) {
        System.out.println("cglib动态代理...正在注册用户，用户信息为：" + user);
    }
}
