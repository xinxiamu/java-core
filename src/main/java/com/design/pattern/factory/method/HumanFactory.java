package com.design.pattern.factory.method;

import java.lang.reflect.InvocationTargetException;

//人类工厂。制造人类
public class HumanFactory {

    public static Human createHuman(Class clazz) {
        Human human = null;

        try {
            human = (Human) Class.forName(clazz.getName()).getDeclaredConstructor().newInstance(); //产生一种人类
        } catch (InstantiationException e) {

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return human;
    }
}
