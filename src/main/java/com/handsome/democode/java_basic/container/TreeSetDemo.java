package com.handsome.democode.java_basic.container;

import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<Long> treeSet = new TreeSet<>();
        treeSet.add(4L);
        treeSet.add(14L);
        treeSet.add(42L);
        treeSet.add(1L);
        treeSet.add(114L);
        treeSet.add(40L);
        System.out.println(treeSet.floor(41L));
        System.out.println(treeSet.ceiling(41L));
        System.out.println(treeSet.floor(100L));
    }
}
