package com.handsome.democode.encryption.Ecdh;

public class Alice {

    /**
     * p,g是双方约定
     */
    long p = 83;
    long g = 8;

    /**
     * alice选择a=9作为私钥
     */
    long a = 9;

    public long generateA(){
        return (g ^ a) % p;
    }

    public long generateK(long B){
        return (B ^ a) % p;
    }
}
