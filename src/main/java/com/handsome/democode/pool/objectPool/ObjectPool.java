package com.handsome.democode.pool.objectPool;

import java.util.ArrayList;

public abstract class ObjectPool<T> {

    private int num = 10;
    private int max = 50;

    private ArrayList<PooledObject<T>> objects;

    public synchronized void createPool(){
        if(objects != null) {
            return;
        }
        objects = new ArrayList<>();
        for(int i = 0; i < num; i++){
            objects.add(create());
        }
    }

    protected abstract PooledObject<T> create();

    public synchronized T getObject() {
        if(objects == null) {
            return null;
        }
        T t = getFreeObject();
        while(t == null) {
            try {
                wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t = getFreeObject();
        }
        return t;
    }

    private T getFreeObject(){
        T t = findFreeObject();
        if(t == null) {
            createObjects(10);
            t = findFreeObject();
        }
        return t;
    }

    private void createObjects(int i) {
        while (i-- > 0) {
            if(objects.size() >= max) {
                return;
            }
            objects.add(create());
        }
    }

    private T findFreeObject(){
        for(PooledObject<T> o : objects) {
            if(!o.isBusy()) {
                o.setBusy(true);
                return o.getObject();
            }
        }
        return null;
    }

    public void releaseObject(T t){
        if(t == null) {
            return;
        }
        for(PooledObject<T> p : objects) {
            if(p.getObject() == t) {
                p.setBusy(false);
                return;
            }
        }
    }

}
