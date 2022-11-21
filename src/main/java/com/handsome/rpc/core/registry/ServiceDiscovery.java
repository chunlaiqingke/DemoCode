package com.handsome.rpc.core.registry;

import java.net.InetSocketAddress;

public interface ServiceDiscovery {

    InetSocketAddress lockupService(String serviceName);

    void register(String serviceName, InetSocketAddress inetSocketAddress);
}
