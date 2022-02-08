package com.adgai.myrpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 代表 server 的一个实例
 * 表示一个具体服务
 * */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
