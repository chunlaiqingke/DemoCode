package com.handsome.rpc.common.serializer;

public class KryoSerializer extends Serializer {

    @Override
    public <T> byte[] serialize(T obj) {
        return new byte[0];
    }

    @Override
    public <T> Object deserialize(byte[] bytes, Class<T> clazz) {
        return null;
    }
}
