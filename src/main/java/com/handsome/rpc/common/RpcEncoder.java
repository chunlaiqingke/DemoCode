package com.handsome.rpc.common;

import com.handsome.rpc.common.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> className;
    private Serializer serializer;

    public RpcEncoder(Serializer serializer, Class<?> className){
        this.serializer = serializer;
        this.className = className;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if(className.isInstance(msg)){
            try {
                byte[] bytes = serializer.serialize(msg);
                out.writeInt(bytes.length);
                out.writeBytes(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
