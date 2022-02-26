package com.adgai.myrpc.exception;

/**
 * 序列化异常
 *
 * @author adgai
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String msg) {
        super(msg);
    }
}
