package com.adgai.myrpc.test;

import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.registry.DefaultServiceRegistry;
import com.adgai.myrpc.registry.ServiceRegistry;
import com.adgai.myrpc.serializer.HessianSerializer;
import com.adgai.myrpc.socket.server.SocketServer;

/**
 * 测试用服务提供方（服务端）
 *
 * @author adgai
 */
public class SocketTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.setSerializer(new HessianSerializer());
        socketServer.start(9999);
    }

}
