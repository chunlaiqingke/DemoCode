package com.handsome.democode.netty.basic.protobuf;

import com.handsome.netty.proto.DataInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtoClient {
    public static void main(String[] args) {
        new ProtoClient().connect("localhost", 8080);
    }

    public void connect(String ip, int port){
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ProtobufVarint32FrameDecoder());
                            pipeline.addLast(new ProtobufDecoder(DataInfo.ResponseBank.getDefaultInstance()));
                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                            pipeline.addLast(new ProtobufEncoder());
                            pipeline.addLast(new SimpleChannelInboundHandler<DataInfo.ResponseBank>() {
                                @Override
                                protected void messageReceived(ChannelHandlerContext ctx, DataInfo.ResponseBank msg) throws Exception {
                                    System.out.println(msg.getBankName());
                                    System.out.println(msg.getBankNo());
                                    System.out.println(msg.getMoney());
                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    DataInfo.RequestUser user = DataInfo.RequestUser.newBuilder()
                                            .setUserName("jerry1.zhao").setAge(27).setPassword("123456").build();
                                    ctx.channel().writeAndFlush(user);
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
