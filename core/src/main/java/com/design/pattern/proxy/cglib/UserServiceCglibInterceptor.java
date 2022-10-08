package com.design.pattern.proxy.cglib;

import com.design.pattern.proxy.User;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceCglibInterceptor implements MethodInterceptor {
    private Object realObject; //被代理类

    public UserServiceCglibInterceptor(Object realObject) {
        super();
        this.realObject = realObject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (objects != null && objects.length > 0 && objects[0] instanceof User) {
            //增强判断
            User user = (User) objects[0];
            if (user.getName().length() <= 1) {
                throw new RuntimeException("用户名称长度必须大于1");
            }
            if (user.getName().length() > 6) {
                throw new RuntimeException("用户名称长度不能大于6");
            }
        }
        Object result = method.invoke(realObject, objects);
        System.out.println("用户注册成功...");
        return result;
    }
}
