package com.adgai.myrpc.test;

import com.adgai.myrpc.RpcClient;
import com.adgai.myrpc.RpcClientProxy;
import com.adgai.myrpc.api.HelloObject;
import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.netty.client.NettyClient;
import com.adgai.myrpc.serializer.HessianSerializer;

/**
 * 测试用Netty消费者
 *
 * @author adgai
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1", 9999);
        client.setSerializer(new HessianSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);

    }

}