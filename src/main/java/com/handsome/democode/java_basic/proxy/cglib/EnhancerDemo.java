package com.handsome.democode.java_basic.proxy.cglib;

import com.handsome.democode.java_basic.proxy.meta.SampleClass;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.FixedValue;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EnhancerDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("beffffffffff");
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println("afteeeeeeee");
                return invoke;
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.method();
    }
}
