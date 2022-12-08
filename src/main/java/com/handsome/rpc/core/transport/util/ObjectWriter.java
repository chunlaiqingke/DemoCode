package com.handsome.rpc.core.transport.util;

import com.handsome.rpc.common.enums.PackageType;
import com.handsome.rpc.common.param.RpcRequest;
import com.handsome.rpc.core.serializer.CommonSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectWriter {

    private static final int MAGIC_NUMBER = 0xCAFEBABE;

    /**
     * 此处赋值顺序是在定协议, 编码
     * int magic_num
     * int packageType
     * int serializerCode
     * int length
     * byte[] data
     * @param outputStream
     * @param obj
     * @param serializer
     * @throws IOException
     */
    public static void writeObject(OutputStream outputStream, Object obj, CommonSerializer serializer) throws IOException {
        outputStream.write(intToBytes(MAGIC_NUMBER));
        if(obj instanceof RpcRequest) {
            outputStream.write(intToBytes(PackageType.REQUEST_PACK.getCode()));
        } else {
            outputStream.write(intToBytes(PackageType.RESPONSE_PACK.getCode()));
        }
        outputStream.write(intToBytes(serializer.getCode()));
        byte[] bytes = serializer.serialize(obj);
        outputStream.write(intToBytes(bytes.length));
        outputStream.write(bytes);
        outputStream.flush();
    }

    private static byte[] intToBytes(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static void main(String[] args) {
        byte[] bytes = intToBytes(128);
        System.out.println(bytes);
    }
}
