package com.handsome.democode.java_basic.thread;

public class SleepInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("thread start");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("interrupt exception");
            }
        });
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
