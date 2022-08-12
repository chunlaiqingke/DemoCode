package com.handsome.democode.netty.basic.httpProtocal;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus badRequest) {

    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if(msg.getDecoderResult().isSuccess()) {
            sendError(ctx, BAD_REQUEST);
            return;
        }
    }
}
