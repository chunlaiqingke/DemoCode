package com.handsome.democode.jvm.lock.SynchronizedDemo;

public class SynchronizedDemo {

    public static void main(String[] args) {
        System.out.println();
        Object o = new Object();
        synchronized (SynchronizedDemo.class) {
            synchronized (o) {
                System.out.println(1);
            }
        }
    }
}
