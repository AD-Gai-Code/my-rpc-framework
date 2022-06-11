package com.adgai.myrpc.transport.socket.client;

import com.adgai.myrpc.loadbalancer.LoadBalancer;
import com.adgai.myrpc.loadbalancer.RandomLoadBalancer;
import com.adgai.myrpc.registry.NacosServiceDiscovery;
import com.adgai.myrpc.registry.ServiceDiscovery;
import com.adgai.myrpc.serializer.CommonSerializer;
import com.adgai.myrpc.transport.socket.util.ObjectReader;
import com.adgai.myrpc.transport.socket.util.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adgai.myrpc.transport.RpcClient;
import com.adgai.myrpc.entity.RpcRequest;
import com.adgai.myrpc.entity.RpcResponse;
import com.adgai.myrpc.enumeration.ResponseCode;
import com.adgai.myrpc.enumeration.RpcError;
import com.adgai.myrpc.exception.RpcException;
import com.adgai.myrpc.util.RpcMessageChecker;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket方式远程方法调用的消费者（客户端）
 *
 * @author adgai
 */
public class SocketClient implements RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private final ServiceDiscovery serviceDiscovery;

    private final CommonSerializer serializer;

    public SocketClient() {
        this(DEFAULT_SERIALIZER, new RandomLoadBalancer());
    }
    public SocketClient(LoadBalancer loadBalancer) {
        this(DEFAULT_SERIALIZER, loadBalancer);
    }
    public SocketClient(Integer serializer) {
        this(serializer, new RandomLoadBalancer());
    }

    public SocketClient(Integer serializer, LoadBalancer loadBalancer) {
        this.serviceDiscovery = new NacosServiceDiscovery(loadBalancer);
        this.serializer = CommonSerializer.getByCode(serializer);
    }

    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        if(serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest.getInterfaceName());
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            ObjectWriter.writeObject(outputStream, rpcRequest, serializer);
            Object obj = ObjectReader.readObject(inputStream);
            RpcResponse rpcResponse = (RpcResponse) obj;
            if (rpcResponse == null) {
                logger.error("服务调用失败，service：{}", rpcRequest.getInterfaceName());
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            if (rpcResponse.getStatusCode() == null || rpcResponse.getStatusCode() != ResponseCode.SUCCESS.getCode()) {
                logger.error("调用服务失败, service: {}, response:{}", rpcRequest.getInterfaceName(), rpcResponse);
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            RpcMessageChecker.check(rpcRequest, rpcResponse);
            return rpcResponse;
        } catch (IOException e) {
            logger.error("调用时有错误发生：", e);
            throw new RpcException("服务调用失败: ", e);
        }
    }

}
