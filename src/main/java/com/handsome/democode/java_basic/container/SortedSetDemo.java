package com.handsome.democode.java_basic.container;

import java.util.*;

public class SortedSetDemo {

    public static void main(String[] args) {
        TreeSet<Node> treeSet = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.i - o2.i;
            }
        });

        TreeMap<String, Node> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        new Node(1, "111");
        new Node(3, "333");
        new Node(2, "222");
        new Node(5, "555");
        new Node(4, "444");
        treeSet.addAll(
                Arrays.asList(
                    new Node(1, "111"),
                    new Node(3, "333"),
                    new Node(2, "222"),
                    new Node(5, "555"),
                    new Node(4, "444"))
        );
        System.out.println(treeSet.pollFirst());
        System.out.println(treeSet.pollFirst());
        System.out.println(treeSet.pollFirst());
    }
}
