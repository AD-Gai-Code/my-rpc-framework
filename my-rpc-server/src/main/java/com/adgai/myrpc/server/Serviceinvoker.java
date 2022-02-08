package com.adgai.myrpc.server;

import com.adgai.myrpc.Request;
import com.adgai.myrpc.common.utils.ReflectionUtils;

/***
 * 调用具体服务
 *
 */

public class Serviceinvoker {
    public Object invoker(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters()
        );
    }
}
