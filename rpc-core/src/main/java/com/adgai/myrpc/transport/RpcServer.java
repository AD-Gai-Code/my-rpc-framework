package com.adgai.myrpc.transport;

import com.adgai.myrpc.serializer.CommonSerializer;

/**
 * 服务器类通用接口
 *
 * @author adgai
 */
public interface RpcServer {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    void start();

    <T> void publishService(T service, String serviceName);

}
