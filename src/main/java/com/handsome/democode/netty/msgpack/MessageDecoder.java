package com.handsome.democode.netty.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        int i = buf.readableBytes();
        byte[] bytes = new byte[i];
        buf.getBytes(buf.readerIndex(), bytes, 0, i);
        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(bytes));
    }
}
