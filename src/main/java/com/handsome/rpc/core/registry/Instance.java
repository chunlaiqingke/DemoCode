package com.handsome.rpc.core.registry;

import lombok.Data;

@Data
public class Instance implements Comparable<Instance>{

    private String ip;

    private int port;

    /**
     *加权轮询需要的字段
     */
    private final Integer weight;
    private Integer effectiveWeight;
    private Integer currentWeight;

    public Instance(Integer weight){
        this.weight = weight;
    }

    public Instance(String ip, int port, Integer weight, Integer effectiveWeight, Integer currentWeight){
        this.ip = ip;
        this.port = port;
        this.weight = weight;
        this.currentWeight = currentWeight;
        this.effectiveWeight = effectiveWeight;
    }

    @Override
    public int compareTo(Instance o) {
        return currentWeight > o.currentWeight ? 1 : (currentWeight.equals(o.currentWeight) ? 0 : -1);
    }
}
