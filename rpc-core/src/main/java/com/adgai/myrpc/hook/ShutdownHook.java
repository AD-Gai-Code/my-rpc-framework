package com.adgai.myrpc.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adgai.myrpc.factory.ThreadPoolFactory;
import com.adgai.myrpc.util.NacosUtil;

/**
 * @author ziyang
 */
public class ShutdownHook {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private static final ShutdownHook shutdownHook = new ShutdownHook();

    public static ShutdownHook getShutdownHook() {
        return shutdownHook;
    }

    public void addClearAllHook() {
        logger.info("关闭后将自动注销所有服务");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            NacosUtil.clearRegistry();
            ThreadPoolFactory.shutDownAll();
        }));
    }

}