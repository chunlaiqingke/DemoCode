package com.handsome.democode.ratelimit.funnel;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 漏斗模型的限流设计，和常规线程池和队列的异步实现方式，这个是同步的
 */
public class Funnel {

    /**
     * 容量
     */
    private int capacity;
    /**
     * 流速
     */
    private float leakingRate;
    /**
     * 剩余空间
     */
    private int leftQuota;
    /**
     * 上次流出的时间
     */
    private long leakingTs;

    ReentrantLock lock = new ReentrantLock(true);

    public Funnel(){

    }

    public Funnel(int capacity, int count, int perSeconde) {
        this.capacity = capacity;
        perSeconde *= 1000;
        this.leakingRate = (float) count / perSeconde;
        leakingTs = System.currentTimeMillis();
    }

    /**
     * 根据上次水流动的时间，腾出已流出的空间
     */
    private void makeSpace(){
        long now = System.currentTimeMillis();
        long interval = now - leakingTs;
        int leaked = (int) (interval * leakingRate);
        if(leaked < 1) {
            return;
        }
        leftQuota += leaked;
        if(leftQuota > capacity) {
            leftQuota = capacity;
        }
        leakingTs = now;
    }

    public boolean watering(int quota){
        try {
            lock.lock();
            makeSpace();
            int left = leftQuota - quota;
            if(left >= 0) {
                leftQuota = left;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
