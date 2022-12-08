package com.handsome.rpc.core.transport;

import com.handsome.rpc.common.param.RpcRequest;
import com.handsome.rpc.common.param.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcClientProxy implements InvocationHandler {

    private final RpcClient rpcClient;

    public RpcClientProxy(RpcClient rpcClient){
        this.rpcClient = rpcClient;
    }

    public <T> T  getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setHeartBeat(false);
        request.setInterfaceName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setParamTypes(method.getParameterTypes());

        RpcResponse<Object> response = null;
        if(rpcClient instanceof SocketRpcClient) {
            response = (RpcResponse) rpcClient.sendRequest(request);
        }

        return response.getData();
    }
}
