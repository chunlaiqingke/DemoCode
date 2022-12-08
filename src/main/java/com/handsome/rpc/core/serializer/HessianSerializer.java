package com.handsome.rpc.core.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianSerializer implements CommonSerializer{
    @Override
    public byte[] serialize(Object o) {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){

        } catch (IOException e) {

        }
        return null;
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
