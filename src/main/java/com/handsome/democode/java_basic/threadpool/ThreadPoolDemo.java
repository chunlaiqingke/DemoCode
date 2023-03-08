package com.handsome.democode.java_basic.threadpool;

import java.util.concurrent.*;

public class ThreadPoolDemo implements Runnable {

    private int id;

    public ThreadPoolDemo(int id){
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + ": " + id);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 0l, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i< 10; i++) {
            threadPoolExecutor.execute(new ThreadPoolDemo(i));
        }
    }
}
