package com.handsome.democode.netty.basic.restful.nio;

import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * 半包粘包问题
 */
public class JavaNioHttpServer implements Runnable {

    public static void main(String[] args) {
        new Thread(new JavaNioHttpServer()).start();
    }

    private static final int DEFAULT_PORT = 8080;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final String INDEX_PAGE = "index.html";
    private static final String STATIC_RESOURCE_DIR = "static";
    private static final String META_RESOURCE_DIR_PREFIX = "/meta/";
    private static final String KEY_VALUE_SEPARATOR = ":";
    private static final String CRLF = "\r\n";

    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    private final int port = 8080;

    public JavaNioHttpServer(){
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
                parseHttpRequest(key);
            } else if (key.isConnectable()) {
                //todo
            } else if (key.isWritable()) {
                //todo
            }
        }
    }

    public void parseHttpRequest(SelectionKey key) throws IOException {
        // 从通道中读取请求头数据
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);

        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        String headerStr = new String(bytes);
        // 解析请求头
        Headers headers = parseHeader(headerStr);

        StringBuilder sendStr = new StringBuilder();

        sendStr.append("Http/1.1 200 Ok\r\n");
        sendStr.append("Content-Type:text/html;charset="+ "UTF-8" +"\r\n");
        sendStr.append("\r\n");
        sendStr.append("<html><head><title>显示报文</title></head><body>");
        sendStr.append("接受到请求的报文是：" + "" + "+<br>");

        for (String k : headers.keySet()) {
            sendStr.append(k+ " = " +headers.get(k) + "<br/>");
        }

        sendStr.append("</body></html>");

        ByteBuffer wrap = ByteBuffer.wrap(sendStr.toString().getBytes("UTF-8"));
        channel.write(wrap);
        channel.close();
    }

    private Headers parseHeader(String headerStr) {
        if (Objects.isNull(headerStr) || headerStr.isEmpty()) {
            throw new RuntimeException();
        }

        // 解析请求头第一行
        int index = headerStr.indexOf(CRLF);
        if (index == -1) {
            throw new RuntimeException();
        }

        Headers headers = new Headers();
        String firstLine = headerStr.substring(0, index);
        String[] parts = firstLine.split(" ");

        /*
         * 请求头的第一行必须由三部分构成，分别为 METHOD PATH VERSION
         * 比如：
         *     GET /index.html HTTP/1.1
         */
        if (parts.length < 3) {
            throw new RuntimeException();
        }

        headers.add("method", parts[0]);
        headers.add("path", parts[1]);
        headers.add("version", parts[2]);

        // 解析请求头属于部分
        parts = headerStr.split(CRLF);
        for (String part : parts) {
            index = part.indexOf(KEY_VALUE_SEPARATOR);
            if (index == -1) {
                continue;
            }
            String key = part.substring(0, index);
            if (index == -1 || index + 1 >= part.length()) {
                headers.set(key, "");
                continue;
            }
            String value = part.substring(index + 1);
            headers.set(key, value);
        }

        return headers;
    }

    private void doWrite(SocketChannel channel, String l) throws IOException {
        byte[] bytes = l.getBytes();
        ByteBuffer buf = ByteBuffer.allocate(bytes.length);
        buf.put(bytes);
        buf.flip();
        channel.write(buf);
    }
}
