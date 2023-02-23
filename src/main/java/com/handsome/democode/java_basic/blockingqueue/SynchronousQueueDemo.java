package com.handsome.democode.java_basic.blockingqueue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> sq = new SynchronousQueue<>();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(100);
                        if(i > 100) {
                            break;
                        }
                        sq.put(i);
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    i += 2;
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Integer poll = null;
                    try {
                        Thread.sleep(100);
                        poll = sq.take();
                        if(poll > 100) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + ": " + (poll + 1));
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
