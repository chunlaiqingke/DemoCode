package com.handsome.democode.java_basic.thread;

public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while(true) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("someone interrupt me...");
                    break;
                } else {
                    System.out.println("thread is running");
                }
            }
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
