package com.handsome.democode.jvm.container.producerConsumer.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer {

    public static int MAX_SIZE = 10;

    public static ArrayList<Integer> list = new ArrayList<>();

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition notEmpty = lock.newCondition();

    public static Condition notFull = lock.newCondition();

    public void produce(int i){
        try {
            lock.lock();
            while (list.size() >= MAX_SIZE) {
                notFull.await();
            }
            list.add(i);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            Producer producer = new Producer();
            for(int i = 0; i < 10000; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                producer.produce(i);
            }
        }).start();

        new Thread(() -> {
            Consumer consumer = new Consumer();
            while (true) {
                consumer.consume();
            }
        }).start();
    }
}
