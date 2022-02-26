package com.adgai.myrpc.test;

import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.netty.server.NettyServer;
import com.adgai.myrpc.registry.DefaultServiceRegistry;
import com.adgai.myrpc.registry.ServiceRegistry;
import com.adgai.myrpc.serializer.KryoSerializer;

/**
 * 测试用Netty服务提供者（服务端）
 *
 * @author adgai
 */
public class NettyTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.setSerializer(new KryoSerializer());
        server.start(9999);
    }

}
