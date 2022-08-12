package com.handsome.democode.java_advanced.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable{

    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    private final int port = 8080;

    public MultiplexerTimeServer(){
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    handle(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(SelectionKey key) throws IOException {
        if(key.isValid()) {
            if(key.isAcceptable()) {

                ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                SocketChannel acceptChannel = channel.accept();
                acceptChannel.configureBlocking(false);
                acceptChannel.register(selector, SelectionKey.OP_READ);

            } else if (key.isReadable()) {
                SocketChannel channel = (SocketChannel)key.channel();
                ByteBuffer buf = ByteBuffer.allocate(1024);
                int read = channel.read(buf);
                if(read > 0) {
                    buf.flip();
                    byte[] bytes = new byte[buf.remaining()];
                    buf.get(bytes);
                    String body = new String(bytes);
                    System.out.println("Time Server receive order: " + body);
                    String response = System.currentTimeMillis() + "";
                    doWrite(channel, response);
                }
            } else if (key.isConnectable()) {
                //todo
            } else if (key.isWritable()) {
                //todo
            }
        }
    }

    private void doWrite(SocketChannel channel, String l) throws IOException {
        byte[] bytes = l.getBytes();
        ByteBuffer buf = ByteBuffer.allocate(bytes.length);
        buf.put(bytes);
        buf.flip();
        channel.write(buf);
    }
}
