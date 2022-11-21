package com.handsome.rpc.core.loadbalance;

import com.handsome.rpc.core.registry.Instance;

import java.util.List;

public interface LoadBalance {

    Instance select(List<Instance> instances);
}
