package com.handsome.democode.netty.basic.restful;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpServer {

    private static final int port = 8080;

    public static void main(String[] args) {

        new HttpServer().bind(port);
    }

    private void bind(int port) {
        EventLoopGroup worker = new NioEventLoopGroup();
        EventLoopGroup boss = new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    //tcp链接的队列大小
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>(){

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast("http-decoder", new HttpRequestDecoder()); // 请求消息解码器
                            pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));// 目的是将多个消息转换为单一的request或者response对象
                            pipeline.addLast("http-encoder", new HttpResponseEncoder());//响应解码器
                            pipeline.addLast(new HttpHelloWorldServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            Channel channel = future.channel();
            ChannelFuture future1 = channel.closeFuture();
            future1.sync();
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
