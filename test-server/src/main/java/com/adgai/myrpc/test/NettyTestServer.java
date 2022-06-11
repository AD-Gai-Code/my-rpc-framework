package com.adgai.myrpc.test;

import com.adgai.myrpc.annotation.ServiceScan;
import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.serializer.CommonSerializer;
import com.adgai.myrpc.transport.RpcServer;
import com.adgai.myrpc.transport.netty.server.NettyServer;
import com.adgai.myrpc.registry.ServiceRegistry;
import com.adgai.myrpc.serializer.KryoSerializer;

/**
 * 测试用Netty服务提供者（服务端）
 *
 * @author adgai
 */
@ServiceScan
public class NettyTestServer {

    //    public static void main(String[] args) {
//        HelloService helloService = new HelloServiceImpl();
//        ServiceRegistry registry = new DefaultServiceRegistry();
//        registry.register(helloService);
//        NettyServer server = new NettyServer();
//        server.setSerializer(new KryoSerializer());
//        server.start(9999);
//    }
    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }

}
