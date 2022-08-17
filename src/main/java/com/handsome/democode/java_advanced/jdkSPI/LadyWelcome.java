package com.handsome.democode.java_advanced.jdkSPI;

public class LadyWelcome implements IWelcome{
    @Override
    public void say() {
        System.out.println("Lady say : welcome");
    }
}
