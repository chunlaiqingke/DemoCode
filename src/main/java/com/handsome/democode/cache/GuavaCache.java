package com.handsome.democode.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.concurrent.TimeUnit;

public class GuavaCache {

    public static void main(String[] args) {
        CacheBuilder.newBuilder().refreshAfterWrite(100, TimeUnit.MILLISECONDS)
                .build(new CacheLoader<Object, Object>() {
                    @Override
                    public Object load(Object key) throws Exception {
                        return null;
                    }
                });
    }
}
