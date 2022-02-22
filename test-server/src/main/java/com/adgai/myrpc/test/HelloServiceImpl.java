package com.adgai.myrpc.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adgai.myrpc.api.HelloObject;
import com.adgai.myrpc.api.HelloService;

/**
 * @author ziyang
 */
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}", object.getMessage());
        return "这是调用的返回值，id=" + object.getId();
    }

}
