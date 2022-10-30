package com.handsome.democode.java_basic.lambda;

public class ThisDemo {

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this);
        }
    };

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this.toString());
        }
    };

    public static void main(String[] args) {
        new ThisDemo().r1.run();
        new ThisDemo().r2.run();
    }

    @Override
    public String toString() {
        return "Hello";
    }
}
