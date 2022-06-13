package com.handsome.democode.java_advanced.nio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable{
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;
    private final String host;
    private final int port;

    public TimeClientHandler(String host, int port){
        this.host = host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void run() {
        try {
            doConnect(socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!stop){
            SelectionKey key = null;
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
//                if(key != null) {
//                    key.cancel();
//                    if(key.channel() != null) {
//                        try {
//                            key.channel().close();
//                        } catch (IOException ioException) {
//                            ioException.printStackTrace();
//                        }
//                    }
//                }
            }
        }

        if(selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel channel = (SocketChannel)key.channel();
            if(key.isConnectable()) {
                if(channel.finishConnect()) {
                    channel.register(selector, SelectionKey.OP_READ);
                    doWrite(channel);
                }
            } else {
                //链接失败，进程退出
                System.exit(1);
            }
            if(key.isReadable()) {
                ByteBuffer buf = ByteBuffer.allocate(1024);
                int read = channel.read(buf);
                if(read > 0) {
                    buf.flip();
                    byte[] bytes = new byte[buf.remaining()];
                    buf.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("now is: " + body);
                    this.stop = true;
                } else if(read < 0){
                    key.cancel();
                    channel.close();
                } else {
                    ;
                }
            }
        }
    }

    private void doWrite(SocketChannel channel) throws IOException {
        byte[] bytes = "Query Time Order".getBytes();
        ByteBuffer writeBuf = ByteBuffer.allocate(bytes.length);
        writeBuf.put(bytes);
        writeBuf.flip();
        channel.write(writeBuf);
        if(!writeBuf.hasRemaining()) {
            System.out.println("Send order 2 server succeed");
        }
    }

    private void doConnect(SocketChannel channel) throws IOException {
        boolean connect = channel.connect(new InetSocketAddress(host, port));
        if(connect) {
            channel.register(selector, SelectionKey.OP_READ);
            doWrite(channel);
        } else {
            channel.register(selector, SelectionKey.OP_CONNECT);
        }
    }
}
