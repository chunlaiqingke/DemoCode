package com.handsome.democode.java_advanced.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileExample {

    private static boolean stop;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int i = 0;
            while(!stop) {
                i++;
                System.out.println(stop);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("subThread end");
        }).start();
        TimeUnit.SECONDS.sleep(3);
        stop = true;
        while(stop) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
