package com.adgai.myrpc.server;

import com.adgai.myrpc.Request;
import com.adgai.myrpc.Response;
import com.adgai.myrpc.codec.Decoder;
import com.adgai.myrpc.codec.Encoder;
import com.adgai.myrpc.common.utils.ReflectionUtils;
import com.adgai.myrpc.transport.RequestHandler;
import com.adgai.myrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {

    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private Serviceinvoker serviceinvoker;

    public RpcServer(){
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;

        this.net = ReflectionUtils.newInstance(
                config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        this.serviceManager = new ServiceManager();
        this.serviceinvoker = new Serviceinvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive, recive.available());
                Request request = decoder.decode(inBytes, Request.class);

                log.info("get request : {}", request);


                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceinvoker.invoker(sis, request);
                resp.setData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("RpsServer got error : " + e.getClass().getName() + " : " + e.getMessage());
            } finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    toResp.write(outBytes);

                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                    e.printStackTrace();
                }
            }
        }
    };
}
