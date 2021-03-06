package com.adgai.myrpc.transport;

import com.adgai.myrpc.entity.RpcRequest;
import com.adgai.myrpc.serializer.CommonSerializer;


/**
 * 客户端类通用接口
 *
 * @author adgai
 */
public interface RpcClient {

    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);

}
