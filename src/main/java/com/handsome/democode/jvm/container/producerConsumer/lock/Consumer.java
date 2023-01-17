package com.handsome.democode.jvm.container.producerConsumer.lock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consumer {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void consume(){
        try {
            Producer.lock.lock();
            while(Producer.list.size() <= 0){
                Producer.notEmpty.await();
            }
            Integer integer = Producer.list.get(0);
            Thread.sleep(integer);
            Producer.list.remove(0);
            System.out.println(simpleDateFormat.format(new Date()) + ":   " + integer + ", queue size :  " + Producer.list.size());
            Producer.notFull.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Producer.lock.unlock();
        }
    }
}
