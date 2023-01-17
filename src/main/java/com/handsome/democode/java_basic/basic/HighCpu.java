package com.handsome.democode.java_basic.basic;

import java.io.FileInputStream;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HighCpu {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            while (true) {
                double a = 231233212312.0002372437467241;
                double v = a / 3.1415926;
                System.out.println(v);
                Date date = new Date();
                if(date.after(new Date("2022-12-11 17:20:00"))) {
                    break;
                }
            }
            return null;
        });
        System.out.println("end");
    }
}
