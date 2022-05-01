package com.handsome.democode.encryption.Ecdh;

public class Bob {

    /**
     * p,g是双方约定
     */
    long p = 83;
    long g = 8;

    /**
     * bob选择b=21作为私钥
     */
    long b = 21;

    public long generateB(long A){
        return (g ^ b) % p;
    }

    public long generateK(long A){
        return (A ^ b) % p;
    }
}
