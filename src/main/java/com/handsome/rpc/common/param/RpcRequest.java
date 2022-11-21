package com.handsome.rpc.common.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求的协议对象
 */
@Data
public class RpcRequest implements Serializable {

    private String requestId;

    private String interfaceName;

    private String methodName;

    private Object[] parameters;

    private Class<?>[] paramTypes;

    /**
     * 是否是心跳包
     */
    private boolean heartBeat;
}
