package com.handsome.democode.jvm.container.producerConsumer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consumer {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public void consume() throws InterruptedException {
        while(true) {
            Integer take = Producer.queue.take();
            Thread.sleep(take);
            String format = simpleDateFormat.format(new Date());
            System.out.println(format + ":  " + take + "  queue size:  " + Producer.queue.size());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                new Producer().produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new Consumer().consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
