package com.handsome.democode.design_pattern.chain;

public class CheckBlacklistHandler extends Handler{
    @Override
    public void doFilter(Request request, Response response) {
        System.out.println("CheckBlacklistHandler");
    }
}
