package com.handsome.democode.java_advanced.concurrent;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapDemo {

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> cslm = new ConcurrentSkipListMap<>();

        cslm.put(3, "Geeks");
        cslm.put(2, "from");
        cslm.put(1, "Hi!");
        cslm.put(5, "Geeks");
        cslm.put(4, "for");

        Integer integer = cslm.firstKey();
    }
}
