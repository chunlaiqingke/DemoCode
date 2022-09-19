package com.handsome.democode.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CuratorClient {

    public static void main(String[] args) throws Exception {
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 1))
                .connectString("127.0.0.1:2181").build();
        curator.start();
        Stat stat = new Stat();
        List<String> strings = curator.getChildren().storingStatIn(stat).forPath("/zookeeper");
        System.out.println(strings);
        curator.close();
    }

}
