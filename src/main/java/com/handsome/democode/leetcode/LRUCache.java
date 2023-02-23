package com.handsome.democode.leetcode;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LRUCache {



    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        int i1 = lruCache.get(1);
        lruCache.put(3,3);
        int i2 = lruCache.get(2);
        lruCache.put(4,4);
        int i3 = lruCache.get(1);
        int i4 = lruCache.get(3);
        int i5 = lruCache.get(4);
        System.out.println("" + i1 + ", "+ i2 + ", " + i3 + ", " +  i4 + ", " + i5);
    }

    HashMap<Integer, Integer> data = new HashMap<>();

    LinkedList<Integer> keys = new LinkedList<>();

    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        //使用，调整数据先后
        if(keys.contains(key)) {
            keys.remove(new Integer(key));
            keys.addFirst(key);
        }
        return data.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if(keys.contains(key)) {
            keys.remove(key);
            keys.addFirst(key);
            data.put(key, value);
        } else {
            if(keys.size() < capacity) {
                keys.addFirst(key);
                data.put(key, value);
            } else {
                if(keys.size() > 0) {
                    int kick = keys.pollLast();
                    data.remove(kick);
                }
                keys.addFirst(key);
                data.put(key, value);
            }
        }
    }
}
