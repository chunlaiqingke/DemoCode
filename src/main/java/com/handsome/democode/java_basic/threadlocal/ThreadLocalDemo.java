package com.handsome.democode.java_basic.threadlocal;

import java.lang.ref.WeakReference;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(10);
        System.out.println(threadLocal.get());

        Thread.sleep(1000);

        System.gc();

        System.out.println(threadLocal.get());

//        weakReference();
    }

    public static void weakReference(){
        WeakReference<Integer> weakReference = new WeakReference<>(new Integer(1));
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(weakReference.get());
        System.gc();//遇到gc就回收了
        System.out.println(weakReference.get());
    }
}
