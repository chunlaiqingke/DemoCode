package com.handsome.democode.java_basic.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2个线程交替打印数字
 */
public class MultiThread1 {
    public static int i = 0;


    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();

        Condition condition1 = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();

        new Thread(() -> {
            while(i++ < 100) {
                try {
                    reentrantLock.lock();
                    condition1.signal();
                    System.out.println(Thread.currentThread().getName() + ":   " + i);
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while(i++ < 100) {
                try {
                    reentrantLock.lock();
                    condition2.signal();
                    System.out.println(Thread.currentThread().getName() + ":  " + i);
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }).start();


    }
}
