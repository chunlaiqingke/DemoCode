package com.handsome.democode.java_basic.container;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.addAll(
                Arrays.asList(
                        new Node(1, "111"),
                        new Node(3, "333"),
                        new Node(2, "222"),
                        new Node(5, "555"),
                        new Node(4, "444"))
        );

    }
}
