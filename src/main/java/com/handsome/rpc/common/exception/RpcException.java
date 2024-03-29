package com.handsome.rpc.common.exception;


import com.handsome.rpc.common.enums.RpcError;

/**
 * RPC调用异常
 *
 * @author ziyang
 */
public class RpcException extends RuntimeException {

    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

}
