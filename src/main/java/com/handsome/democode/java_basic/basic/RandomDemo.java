package com.handsome.democode.java_basic.basic;

import java.util.Random;

public class RandomDemo {

    public static void main(String[] args) {
        Random random = new Random();
        for(int i = 0; i< 10000000; i++) {
            int i1 = random.nextInt(1);
            if(i1 != 0) {
                System.out.println(i);
                System.out.println(i1);
                break;
            }
        }
    }
}
