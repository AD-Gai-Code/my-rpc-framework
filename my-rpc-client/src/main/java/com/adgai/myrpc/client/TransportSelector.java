package com.adgai.myrpc.client;

import com.adgai.myrpc.Peer;
import com.adgai.myrpc.transport.TransportClient;

import java.util.List;

/**
 * 表示选择哪个 server 去连接
 */
public interface TransportSelector {
    /**
     * 初始化 selector
     * @param peers 可以连接的 server 端信息
     * @param count Client 与 server 建立多少连接
     * @param clazz client 实现 class
     */
    void init(List<Peer> peers,
              int count,
              Class<? extends  TransportClient> clazz);
    /**
     * 选择一个 transport 与 server 做交互
     * @return
     */
    TransportClient select();

    /**
     * 释放用完的 client
     * @param client
     */
    void release(TransportClient client);

    void close();
}
