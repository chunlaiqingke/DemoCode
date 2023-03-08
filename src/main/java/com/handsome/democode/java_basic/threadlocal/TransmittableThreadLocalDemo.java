package com.handsome.democode.java_basic.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalDemo {

    public static void main(String[] args) {
        TransmittableThreadLocal<Object> ttl = new TransmittableThreadLocal<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);
    }
}
