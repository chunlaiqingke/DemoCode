package com.handsome.rpc.common;

import com.handsome.rpc.core.serializer.CommonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> className;
    private CommonSerializer serializer;

    public RpcDecoder(CommonSerializer serializer, Class<?> className){
        this.serializer = serializer;
        this.className = className;
    }

    public RpcDecoder(){
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //无法读取长度字段
        if(in.readableBytes() < 4) {
            return;
        }
        //半包处理
        in.markReaderIndex();
        int dataLength = in.readInt();
        if(in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        //解析数据
        byte[] bytes = new byte[dataLength];
        in.readBytes(bytes);
        try {
            Object obj = serializer.deserialize(bytes, className);
            out.add(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
