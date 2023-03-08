package com.handsome.democode.java_basic.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolQueueDemo {

    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(10);
        ArrayBlockingQueue<Runnable> m= new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.MILLISECONDS, m);
        myBlockingQueue.setExecutor(executor);
        for(int i = 0; i<10; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": 线程数：" + executor.getPoolSize() + ", 队列长度：" + m.size());
                }
            });
        }
    }

    static class MyBlockingQueue extends ArrayBlockingQueue<Runnable> {

        public void setExecutor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        private ThreadPoolExecutor executor;

        public MyBlockingQueue(int capacity) {
            super(capacity);
        }

        @Override
        public boolean offer(Runnable o) {
            if(executor.getMaximumPoolSize() > executor.getPoolSize()) {
                return false;
            }
            return super.offer(o);
        }
    }
}
