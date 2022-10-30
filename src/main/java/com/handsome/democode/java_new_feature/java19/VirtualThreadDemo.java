package com.handsome.democode.java_new_feature.java19;

public class VirtualThreadDemo {

    private static void virtualTread() throws InterruptedException {
        Thread.startVirtualThread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).join();

        Thread.startVirtualThread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).join();

        Thread.startVirtualThread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).join();

        Thread.startVirtualThread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).join();
    }

    private static void thread(){
        new Thread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).start();
        new Thread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).start();
        new Thread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).start();
        new Thread(() -> {
            double result = 0.0;
            int i = 10000000;
            while(i-- > 0){
                result += i / 3.1415926;
            }
            System.out.println(result);
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        thread();
    }
}
