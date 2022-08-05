package com.handsome.democode.java_advanced.nio;

public class TimeServer {

    public static void main(String[] args) {
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer();
        new Thread(multiplexerTimeServer).start();
    }
}
