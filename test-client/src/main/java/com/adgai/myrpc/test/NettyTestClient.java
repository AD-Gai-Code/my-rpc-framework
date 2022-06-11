package com.adgai.myrpc.test;

import com.adgai.myrpc.api.ByeService;
import com.adgai.myrpc.serializer.CommonSerializer;
import com.adgai.myrpc.transport.RpcClient;
import com.adgai.myrpc.transport.RpcClientProxy;
import com.adgai.myrpc.api.HelloObject;
import com.adgai.myrpc.api.HelloService;
import com.adgai.myrpc.transport.netty.client.NettyClient;
import com.adgai.myrpc.serializer.HessianSerializer;

/**
 * 测试用Netty消费者
 *
 * @author adgai
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.PROTOBUF_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
        ByeService byeService = rpcClientProxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }

}
