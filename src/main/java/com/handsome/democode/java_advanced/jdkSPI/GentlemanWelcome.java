package com.handsome.democode.java_advanced.jdkSPI;

public class GentlemanWelcome implements IWelcome{
    @Override
    public void say() {
        System.out.println("gentleman say : welcome");
    }
}
