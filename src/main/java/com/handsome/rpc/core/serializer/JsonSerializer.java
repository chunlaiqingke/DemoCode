package com.handsome.rpc.core.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handsome.rpc.common.enums.SerializerCode;

import java.io.IOException;

public class JsonSerializer implements CommonSerializer{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(Object o) {
        try {
            return objectMapper.writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化发生错误", e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes, Class<?> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException("反序列化发生失败", e);
        }
    }

    @Override
    public int getCode() {
        return SerializerCode.JSON.getCode();
    }
}
