package com.handsome.democode.java_advanced.bio;

import java.io.*;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", 9000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new PrintWriter(socket.getOutputStream(), true);
//            out.println("query time order");
            socket.getOutputStream().write("query time order string".getBytes());
            String s = in.readLine();
            System.out.println("now is " + s);
        } finally {
            assert socket != null;
            socket.close();
            assert in != null;
            in.close();
            assert out != null;
            out.close();
        }
    }
}
