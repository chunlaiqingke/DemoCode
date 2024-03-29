package com.handsome.democode.ratelimit.token;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class IntTokenBucket {
    private final int DEFAULT_BUCKET_SIZE = 10;

    private volatile AtomicInteger token = new AtomicInteger(DEFAULT_BUCKET_SIZE);

    private volatile boolean start = false;

    //定时放令牌的执行器
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private ReentrantLock lock = new ReentrantLock(true);

    public void addToken(int tokenNum){
        try {
            lock.lock();
            for(int i = 0; i< tokenNum; i++) {
                if(token.get() >= DEFAULT_BUCKET_SIZE) {
                    break;
                }
                token.incrementAndGet();
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean getToken(int tokenNum){
        try {
            lock.lock();
            if(token.get() < tokenNum) {
                return false;
            }
            for(int i = 0; i< tokenNum; i++) {
                token.decrementAndGet();
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void start(){
        executor.scheduleAtFixedRate(() -> {
            addToken(1);
        }, 0, 1, TimeUnit.SECONDS);
        start = true;
    }

    public static void main(String[] args) throws InterruptedException {
        IntTokenBucket intTokenBucket = new IntTokenBucket();
        intTokenBucket.start();
//        for(int i = 0; i < 200; i++) {
//            Thread.sleep(1000);
//            boolean token = intTokenBucket.getToken(4);
//            if(token) {
//                System.out.println("pass");
//            } else {
//                System.out.println("fail");
//            }
//        }
        for(int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean token = intTokenBucket.getToken(2);
                    if(token) {
                        System.out.println("pass");
                    } else {
                        System.out.println("fail");
                    }
                }
            }).start();
        }
    }

}
