package com.handsome.democode.java_basic.proxy.static_proxy;

public class RealProxy extends Real{

    private Real real;

    public RealProxy(Real real){
        this.real = real;
    }

    @Override
    public String demo() {
        System.out.println("before");
        String demo = super.demo();
        System.out.println("after");
        return demo;
    }

    public static void main(String[] args) {
        Real real1 = new Real();
        real1 = new RealProxy(real1);
        System.out.println(real1.demo());
    }
}
