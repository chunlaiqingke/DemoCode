package com.handsome.rpc.common.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse<T> implements Serializable {

    private String requestId;

    private Integer status;

    private String message;

    private T data;
}
