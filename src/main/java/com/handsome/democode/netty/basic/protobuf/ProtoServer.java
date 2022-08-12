package com.handsome.democode.netty.basic.protobuf;

import com.handsome.netty.proto.DataInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtoServer {
    public static void main(String[] args) {
        new Server().bind(8080);
    }

    public static class Server{
        private int port;

        public void bind(int port){
            NioEventLoopGroup worker = new NioEventLoopGroup();
            NioEventLoopGroup boss = new NioEventLoopGroup();

            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(worker, boss)
                        .channel(NioServerSocketChannel.class)
                        .childOption(ChannelOption.SO_BACKLOG, 1024)
                        .childHandler(new ChannelInitializer<SocketChannel>() {

                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
                                pipeline.addLast(new ProtobufVarint32FrameDecoder());
                                //服务器端接收的是客户端RequestUser对象，所以这边将接收对象进行解码生产实列
                                pipeline.addLast(new ProtobufDecoder(DataInfo.RequestUser.getDefaultInstance()));
                                //Google Protocol Buffers编码器
                                pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                                //Google Protocol Buffers编码器
                                pipeline.addLast(new ProtobufEncoder());
                                pipeline.addLast(new ProtoServerHandler());
                            }
                        });
                ChannelFuture future = bootstrap.bind(port).sync();
                Channel channel = future.channel();
                channel.closeFuture().sync();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                worker.shutdownGracefully();
                boss.shutdownGracefully();
            }
        }
    }

    public static class ProtoServerHandler extends SimpleChannelInboundHandler<DataInfo.RequestUser>{

        @Override
        protected void messageReceived(ChannelHandlerContext ctx, DataInfo.RequestUser msg) throws Exception {
            System.out.println(msg.getUserName());
            System.out.println(msg.getAge());
            System.out.println(msg.getPassword());

            DataInfo.ResponseBank bank = DataInfo.ResponseBank.newBuilder().setBankName("中国工商银行")
                    .setBankNo("6222222200000000000").setMoney(560000.23).build();

            ctx.channel().writeAndFlush(bank);
        }
    }
}
