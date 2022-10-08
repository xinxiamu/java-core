package com.design.pattern.proxy.cglib;

import com.design.pattern.proxy.User;
import net.sf.cglib.proxy.Enhancer;

public class CglibTest {

    public static void main(String[] args) {
        User user = new User();
        user.setName("zmt");
        user.setAge(18);
        user.setPassword("123456");

        //设置被代理对象
        UserCglibServiceImpl userCglibService = new UserCglibServiceImpl();
        UserServiceCglibInterceptor interceptor = new UserServiceCglibInterceptor(userCglibService);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userCglibService.getClass());
        enhancer.setCallback(interceptor);

        //动态代理类
        UserCglibServiceImpl cglibProxy = (UserCglibServiceImpl) enhancer.create();
        System.out.println("动态代理类父类：" + cglibProxy.getClass().getSuperclass());
        cglibProxy.addUser(user);
    }
}
