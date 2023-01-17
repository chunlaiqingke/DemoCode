package com.handsome.democode.pool.objectPool;

import java.util.Random;

public class DefaultObjectPool extends ObjectPool<String> {
    @Override
    protected PooledObject<String> create() {
        return new PooledObject<>("" + new Random().nextInt(100));
    }
}
