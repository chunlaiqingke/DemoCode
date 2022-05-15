package com.handsome.democode.java_basic.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {


    public static void main(String[] args) {

    }

    private void test1() throws InterruptedException {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenAccept(result -> {
            System.out.println(1);
        });
    }
}
