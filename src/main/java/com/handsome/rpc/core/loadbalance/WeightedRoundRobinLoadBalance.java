package com.handsome.rpc.core.loadbalance;

import com.handsome.rpc.core.registry.Instance;

import java.util.List;

public class WeightedRoundRobinLoadBalance implements LoadBalance{
    private static Integer totalWeight = 0;

    @Override
    public Instance select(List<Instance> instances) {
        if (instances ==null || instances.size()<=0) return null;
        if (instances.size() == 1)  return instances.get(0);

        Instance nodeOfMaxWeight = null; // 保存轮询选中的节点信息

        synchronized (WeightedRoundRobinLoadBalance.class){
            // 选出当前权重最大的节点
            Instance tempNodeOfMaxWeight = null;
            for (Instance node : instances) {
                if (tempNodeOfMaxWeight == null)
                    tempNodeOfMaxWeight = node;
                else
                    tempNodeOfMaxWeight = tempNodeOfMaxWeight.compareTo(node) > 0 ? tempNodeOfMaxWeight : node;
            }
            // 必须new个新的节点实例来保存信息，否则引用指向同一个堆实例，后面的set操作将会修改节点信息
            nodeOfMaxWeight = new Instance(
                    tempNodeOfMaxWeight.getIp(),
                    tempNodeOfMaxWeight.getPort(),
                    tempNodeOfMaxWeight.getWeight(),
                    tempNodeOfMaxWeight.getEffectiveWeight(),
                    tempNodeOfMaxWeight.getCurrentWeight()
            );

            // 调整当前权重比：按权重（effectiveWeight）的比例进行调整，确保请求分发合理。
            tempNodeOfMaxWeight.setCurrentWeight(tempNodeOfMaxWeight.getCurrentWeight() - totalWeight);


            instances.forEach(node -> node.setCurrentWeight(node.getCurrentWeight()+node.getEffectiveWeight()));

        }
        return nodeOfMaxWeight;
    }
}
