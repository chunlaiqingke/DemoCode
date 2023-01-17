package com.handsome.democode.jvm.container.producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class Producer {

    public static final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public void produce() throws InterruptedException {
        for(int i = 0; i<1000; i++) {
            Thread.sleep(100);
            queue.offer(i);
        }
    }
}
