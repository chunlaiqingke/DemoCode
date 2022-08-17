package com.handsome.democode.java_advanced.jdkSPI;

import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        ServiceLoader<IWelcome> loads = ServiceLoader.load(IWelcome.class);
        for(IWelcome w : loads) {
            w.say();
        }
    }
}
