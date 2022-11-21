package com.handsome.rpc.core.transport;

import com.handsome.rpc.common.param.RpcRequest;

/**
 * rpc接口
 */
public interface RpcClient {

    Object sendRequest(RpcRequest request);
}
