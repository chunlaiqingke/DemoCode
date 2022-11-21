package com.handsome.democode.design_pattern.chain;

public abstract class Handler {

    Handler nextHandler;

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void filter(Request request, Response response){
        doFilter(request, response);
        if(nextHandler != null) {
            nextHandler.filter(request, response);
        }
    }

    public abstract void doFilter(Request request, Response response);
}
