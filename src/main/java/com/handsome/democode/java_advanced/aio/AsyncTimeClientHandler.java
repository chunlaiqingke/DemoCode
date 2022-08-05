package com.handsome.democode.java_advanced.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {

    private String ip;
    private int port;
    private AsynchronousSocketChannel socketChannel;
    private CountDownLatch latch;

    public AsyncTimeClientHandler(String ip, int port){
        this.ip = ip;
        this.port = port;
        try {
            socketChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        byte[] bytes = "Query Time Order".getBytes();
        ByteBuffer writeBuf = ByteBuffer.allocate(1024);
        writeBuf.put(bytes);
        writeBuf.flip();
        socketChannel.write(writeBuf, writeBuf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if(attachment.hasRemaining()) {
                    socketChannel.write(attachment, attachment, this);
                } else {
                    ByteBuffer readBuf = ByteBuffer.allocate(1024);
                    socketChannel.read(readBuf, readBuf, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] bytes1 = new byte[attachment.remaining()];
                            attachment.get(bytes1);
                            String body = new String(bytes1, StandardCharsets.UTF_8);
                            System.out.println("now is : " + body);
                            latch.countDown();
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                socketChannel.close();
                                latch.countDown();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    socketChannel.close();
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        try {
            socketChannel.close();
            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        socketChannel.connect(new InetSocketAddress(ip, port), this, this);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
