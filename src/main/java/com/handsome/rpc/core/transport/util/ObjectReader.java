package com.handsome.rpc.core.transport.util;

import com.handsome.rpc.common.enums.PackageType;
import com.handsome.rpc.common.enums.RpcError;
import com.handsome.rpc.common.exception.RpcException;
import com.handsome.rpc.common.param.RpcRequest;
import com.handsome.rpc.common.param.RpcResponse;
import com.handsome.rpc.core.serializer.CommonSerializer;

import java.io.IOException;
import java.io.InputStream;

public class ObjectReader {

    private static final int MAGIC_NUMBER = 0xCAFEBABE;

    /**
     * 解码，按照协议解析数据
     * @param in
     * @return
     * @throws IOException
     */
    public static Object readObject(InputStream in) throws IOException {
        byte[] numberBytes = new byte[4];
        in.read(numberBytes);
        int magic = byteToInt(numberBytes);
        if(magic != MAGIC_NUMBER) {
            throw new RpcException(RpcError.UNKNOWN_PROTOCOL);
        }
        in.read(numberBytes);
        int packageCode = byteToInt(numberBytes);
        Class<?> packageClass;
        if(packageCode == PackageType.REQUEST_PACK.getCode()) {
            packageClass = RpcRequest.class;
        } else if(packageCode == PackageType.RESPONSE_PACK.getCode()) {
            packageClass = RpcResponse.class;
        } else {
            throw new RpcException(RpcError.UNKNOWN_PACKAGE_TYPE);
        }
        in.read(numberBytes);
        int serializerCode = byteToInt(numberBytes);
        CommonSerializer serializer = CommonSerializer.getByCode(serializerCode);
        if(serializer == null) {
            throw new RpcException(RpcError.UNKNOWN_SERIALIZER);
        }
        in.read(numberBytes);
        int length = byteToInt(numberBytes);
        byte[] bytes = new byte[length];
        in.read(bytes);
        return serializer.deserialize(bytes, packageClass);
    }

    private static int byteToInt(byte[] src) {
        int value;
        value = ((src[0] & 0xFF)<<24)
                |((src[1] & 0xFF)<<16)
                |((src[2] & 0xFF)<<8)
                |(src[3] & 0xFF);
        return value;
    }
}
