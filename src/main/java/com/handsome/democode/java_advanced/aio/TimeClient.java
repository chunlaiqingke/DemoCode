package com.handsome.democode.java_advanced.aio;

public class TimeClient {

    public static void main(String[] args) {
        AsyncTimeClientHandler asyncTimeClientHandler = new AsyncTimeClientHandler("127.0.0.1", 8080);
        new Thread(asyncTimeClientHandler).start();
    }
}
