package com.adgai.myrpc.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @author adgai
 */
public interface LoadBalancer {

    Instance select(List<Instance> instances);

}
