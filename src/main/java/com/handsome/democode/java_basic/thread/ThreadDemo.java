package com.handsome.democode.java_basic.thread;

import java.nio.file.Files;
import java.util.ArrayList;

public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        new ArrayList<>();

    }
}
