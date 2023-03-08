package com.handsome.rpc.core.loadbalance;

import com.handsome.rpc.core.registry.Instance;

import java.util.List;

public class RoundRobinLoadBalance implements LoadBalance{
    @Override
    public Instance select(List<Instance> instances) {
        return null;
    }
}
