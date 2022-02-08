package com.adgai.myrpc.client;

import com.adgai.myrpc.Peer;
import com.adgai.myrpc.codec.Decoder;
import com.adgai.myrpc.codec.Encoder;
import com.adgai.myrpc.codec.JSONDecoder;
import com.adgai.myrpc.codec.JSONEncoder;
import com.adgai.myrpc.transport.HTTPTransportClient;
import com.adgai.myrpc.transport.TransportClient;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass =
            HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass =
            RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
