package com.handsome.rpc.core.transport;

import com.handsome.rpc.common.enums.ResponseCode;
import com.handsome.rpc.common.enums.RpcError;
import com.handsome.rpc.common.exception.RpcException;
import com.handsome.rpc.common.param.RpcRequest;
import com.handsome.rpc.common.param.RpcResponse;
import com.handsome.rpc.core.registry.LocalServiceDiscovery;
import com.handsome.rpc.core.registry.ServiceDiscovery;
import com.handsome.rpc.core.serializer.CommonSerializer;
import com.handsome.rpc.core.transport.util.ObjectReader;
import com.handsome.rpc.core.transport.util.ObjectWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketRpcClient implements RpcClient{

    private final ServiceDiscovery serviceDiscovery;

    private final CommonSerializer serializer;

    public SocketRpcClient(){
        serviceDiscovery = new LocalServiceDiscovery();
        serializer = CommonSerializer.getByCode(CommonSerializer.DEFAULT_SERIALIZER);
    }

    @Override
    public RpcResponse sendRequest(RpcRequest request) {
        InetSocketAddress inetSocketAddress = serviceDiscovery.lockupService(request.getInterfaceName());
        try(Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            ObjectWriter.writeObject(outputStream, request, serializer);
            Object o = ObjectReader.readObject(inputStream);
            RpcResponse rpcResponse = (RpcResponse) o;
            if(rpcResponse == null) {
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE);
            }
            if (rpcResponse.getStatus() == null || rpcResponse.getStatus() != ResponseCode.SUCCESS.getCode()) {
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE);
            }
            return rpcResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
