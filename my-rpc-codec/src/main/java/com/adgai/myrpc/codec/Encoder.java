package com.adgai.myrpc.codec;

/**
 * 序列化
 * */
public interface Encoder {
    byte[] encode(Object obj);
}
