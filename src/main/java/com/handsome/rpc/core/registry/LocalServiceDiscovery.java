package com.handsome.rpc.core.registry;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 为了方便，使用本地注册中心
 */
public class LocalServiceDiscovery implements ServiceDiscovery{

    ConcurrentHashMap<String, Instance> services = new ConcurrentHashMap<>();

    @Override
    public InetSocketAddress lockupService(String serviceName) {
        return null;
    }

    @Override
    public void register(String serviceName, InetSocketAddress inetSocketAddress) {

    }
}
