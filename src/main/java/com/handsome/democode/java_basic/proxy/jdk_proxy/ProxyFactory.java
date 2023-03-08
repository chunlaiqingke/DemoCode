package com.handsome.democode.java_basic.proxy.jdk_proxy;


import com.handsome.democode.java_basic.proxy.meta.SampleClass;
import com.handsome.democode.java_basic.proxy.meta.SampleInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    T target;

    public ProxyFactory(T t){
        target = t;
    }

    public T newInstance(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("beffffffore");
                        method.invoke(target, args);
                        System.out.println("aftttttter");
                        return null;
                    }
                });
    }

    public static void main(String[] args) {
        SampleInterface sampleInterface = new ProxyFactory<SampleInterface>(new SampleClass()).newInstance();
        sampleInterface.method();
    }
}
