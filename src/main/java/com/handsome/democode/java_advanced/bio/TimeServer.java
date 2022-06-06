package com.handsome.democode.java_advanced.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 9000;
        ServerSocket serverSocket = new ServerSocket(port);
        try {
            while(true){
                Socket accept = serverSocket.accept();
                new Thread(new TimeHandler(accept)).start();
            }
        } finally {
            serverSocket.close();
        }
    }

}
