package com.adgai.myrpc.test;

import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.registry.DefaultServiceRegistry;
import com.adgai.myrpc.registry.ServiceRegistry;
import com.adgai.myrpc.server.RpcServer;

/**
 * 测试用服务提供方（服务端）
 * @author ziyang
 */
public class TestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }

}
