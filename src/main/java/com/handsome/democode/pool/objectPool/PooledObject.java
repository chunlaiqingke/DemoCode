package com.handsome.democode.pool.objectPool;

public class PooledObject<T> {
    public T object;
    public boolean busy;

    public PooledObject(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
