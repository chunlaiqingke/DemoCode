package com.handsome.democode.java_basic.thread;

public class ThreadMaxNum {

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i<100000; i++){
            System.out.println(i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}
