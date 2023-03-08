package com.handsome.democode.java_basic.basic;

public class IntegerDemo {

    /**
     * IntegerCache中去拿缓存好的数
     * @param args
     */
    public static void main(String[] args) {
        Integer integer1 = 100;
        Integer integer2 = 100;

        System.out.println(integer1 == integer2);
    }
}
