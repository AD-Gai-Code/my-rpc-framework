package com.adgai.myrpc;

import com.adgai.myrpc.serializer.CommonSerializer;

/**
 * 服务器类通用接口
 *
 * @author adgai
 */
public interface RpcServer {

    void start(int port);

    void setSerializer(CommonSerializer serializer);

}
