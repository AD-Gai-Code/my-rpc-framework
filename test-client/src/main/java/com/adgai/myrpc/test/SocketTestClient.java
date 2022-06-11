package com.adgai.myrpc.test;

import com.adgai.myrpc.transport.RpcClientProxy;
import com.adgai.myrpc.api.HelloObject;
import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.serializer.KryoSerializer;
import com.adgai.myrpc.transport.socket.client.SocketClient;

/**
 * 测试用消费者（客户端）
 *
 * @author adgai
 */
public class SocketTestClient {

    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9999);
        client.setSerializer(new KryoSerializer());
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }

}
