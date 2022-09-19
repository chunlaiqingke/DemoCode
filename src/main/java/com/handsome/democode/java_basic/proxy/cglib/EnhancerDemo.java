package com.handsome.democode.java_basic.proxy.cglib;

import com.handsome.democode.java_basic.proxy.meta.SampleClass;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;

public class EnhancerDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("Proxy method");
                return "Proxy method";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.method();
    }
}
