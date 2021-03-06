package com.adgai.myrpc.provider;

/**
 * 保存和提供服务实例对象
 * @author adgai
 */
public interface ServiceProvider {


    <T> void addServiceProvider(T service, String serviceName);

    Object getServiceProvider(String serviceName);

}
