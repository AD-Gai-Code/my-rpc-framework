package com.adgai.myrpc.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 * */
public class ReflectionUtils {
    /**
     * 根据 calss 创建对象
     *
     * @param  clazz 待创建对象的类型
     * @param <T> 对象类型
     * @return 创建好的对象
     * */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个 class 的公共方法
     *
     * @param clazz
     * @return 当前类声明的公共方法
     * */
    public static Method[] getPublicMethods(Class clazz) {
        // getDeclaredMethods(): 返回当前类所有的方法，不包含父类的方法，同时包含 private和 protected 的方法
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList<>();
        // 把 public 的方法过滤出来
        for(Method m : methods) {
            if(Modifier.isPublic(m.getModifiers())) {
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     *
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 返回的结果
     * */
    public static Object invoke(Object obj,
                                Method method,
                                Object... args) {
        try {
            // obj 表示调哪个对象的这个方法(调用静态方法传 null)，args 是可变参数，把所有的参数放进来
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
