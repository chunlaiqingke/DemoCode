package com.handsome.democode.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();



        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        conditionB.signal();
                        System.out.println("A");
                        conditionA.await();
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                    }
                }

            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        conditionC.signal();
                        System.out.println("B");
                        conditionB.await();
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                    }
                }

            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        conditionA.signal();
                        System.out.println("C");
                        conditionC.await();
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                    }
                }

            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

    }
}
