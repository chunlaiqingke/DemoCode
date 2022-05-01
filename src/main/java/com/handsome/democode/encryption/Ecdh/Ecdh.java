package com.handsome.democode.encryption.Ecdh;

public class Ecdh {

    public static void main(String[] args) {
        Alice alice = new Alice();
        Bob bob = new Bob();
        long A = alice.generateA();
        long B = bob.generateB(A);
        long AK = bob.generateK(A);
        long BK = alice.generateK(B);
        System.out.println(AK == BK);
    }
}
