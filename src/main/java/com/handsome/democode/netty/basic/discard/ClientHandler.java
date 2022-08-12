package com.handsome.democode.netty.basic.discard;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] req = "Discard client request \n Discard client request 2 \n".getBytes();
        ByteBuf buffer = Unpooled.buffer(req.length);
        buffer.writeBytes(req);
        ctx.writeAndFlush(buffer);

//        for(int i = 0; i< 100; i++) {
//            ByteBuf bf = Unpooled.buffer(req.length);
//            bf.writeBytes(req);
//            ctx.writeAndFlush(bf);
//        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println(body);
    }
}
