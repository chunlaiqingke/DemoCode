package com.handsome.democode.java_advanced.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel){
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        String req = new String(body, StandardCharsets.UTF_8);
        System.out.println("time server receive order: " + req);
        String currentTime = "Query Time Order".equalsIgnoreCase(req) ? new Date(System.currentTimeMillis()).toString() : "Bad Request";
        doWrite(currentTime);
    }

    private void doWrite(String currentTime) {
        if(currentTime == null || currentTime.trim().length() <= 0) {
            return;
        }
        byte[] bytes = currentTime.getBytes();
        ByteBuffer writeBuf = ByteBuffer.allocate(bytes.length);
        writeBuf.put(bytes);
        writeBuf.flip();
        channel.write(writeBuf, writeBuf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if(attachment.hasRemaining()){
                    channel.write(attachment, attachment, this);
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
