package com.handsome.democode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class DemoCodeApplicationTests {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (true) {
                    if (i++ % 1000 == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
    }

    @Test
    void contextLoads() {





    }

}
