package com.handsome.democode.java_advanced.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TimeHandler implements Runnable{
    Socket socket;

    public TimeHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();

            int available = inputStream.available();
            byte[] bytes = new byte[available];
            int read = inputStream.read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
