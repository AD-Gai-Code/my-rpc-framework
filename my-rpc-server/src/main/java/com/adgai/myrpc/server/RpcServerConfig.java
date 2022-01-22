package com.adgai.myrpc.server;


import com.adgai.myrpc.codec.Decoder;
import com.adgai.myrpc.codec.Encoder;
import com.adgai.myrpc.codec.JSONDecoder;
import com.adgai.myrpc.codec.JSONEncoder;
import com.adgai.myrpc.transport.HTTPTransportClient;
import com.adgai.myrpc.transport.HttpTransportServeer;
import com.adgai.myrpc.transport.TransportServer;
import lombok.Data;

/**
 * server 配置
 * */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServeer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
