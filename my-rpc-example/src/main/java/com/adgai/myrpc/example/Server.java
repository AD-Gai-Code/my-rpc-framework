package com.adgai.myrpc.example;

import com.adgai.myrpc.server.RpcServer;
import com.adgai.myrpc.server.RpcServerConfig;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
