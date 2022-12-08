package com.handsome.rpc.core.serializer;

public interface CommonSerializer {

    int KRYO_SERIALIZER = 0;
    int JSON_SERIALIZER = 1;
    int HESSIAN_SERIALIZER = 2;
    int PROTOBUF_SERIALIZER = 3;

    int DEFAULT_SERIALIZER = KRYO_SERIALIZER;

    static CommonSerializer getByCode(int code){
        switch (code) {
            case 0:
                return new KryoSerializer();
            case 1:
                return new JsonSerializer();
            case 2:
                return new HessianSerializer();
            case 3:
                return new ProtobufSerializer();
            default:
                return null;
        }
    }

    byte[] serialize(Object o);

    Object deserialize(byte[] bytes, Class<?> clazz);

    int getCode();
}
