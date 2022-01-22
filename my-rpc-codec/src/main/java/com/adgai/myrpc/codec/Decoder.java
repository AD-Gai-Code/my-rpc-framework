package com.adgai.myrpc.codec;


/**
 * 反序列化
 * */
public interface Decoder {
    // 加泛型,省去强制转化类的步骤
    <T> T decode(byte[] bytes, Class<T> calzz);
}
