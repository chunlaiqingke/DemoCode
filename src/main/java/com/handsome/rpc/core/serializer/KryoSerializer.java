package com.handsome.rpc.core.serializer;

public class KryoSerializer implements CommonSerializer{
    @Override
    public byte[] serialize(Object o) {
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes, Class<?> clazz) {
        return null;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
