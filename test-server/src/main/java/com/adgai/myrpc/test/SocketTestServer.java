package com.adgai.myrpc.test;

import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.registry.ServiceRegistry;
import com.adgai.myrpc.serializer.CommonSerializer;
import com.adgai.myrpc.serializer.HessianSerializer;
import com.adgai.myrpc.transport.RpcServer;
import com.adgai.myrpc.transport.socket.server.SocketServer;

/**
 * 测试用服务提供方（服务端）
 *
 * @author adgai
 */
public class SocketTestServer {

    public static void main(String[] args) {
        RpcServer server = new SocketServer("127.0.0.1", 9998, CommonSerializer.HESSIAN_SERIALIZER);
        server.start();
    }

}
