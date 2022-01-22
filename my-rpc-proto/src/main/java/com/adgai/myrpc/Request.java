package com.adgai.myrpc;


import lombok.Data;

/**
* 表示 RPC 的一个请求
* */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;

}
