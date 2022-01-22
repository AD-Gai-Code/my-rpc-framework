package com.adgai.myrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * json 反序列化
 * */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
