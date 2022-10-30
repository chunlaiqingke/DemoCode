package com.handsome.democode.java_basic.lambda;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.merge("3", "4", (v1, v2) -> v1 + v2);
        map.forEach((k, v) -> System.out.println(k + "=" + v));

        Map<String, String> map2 = new HashMap<>();
        map2.put("1", "1");
        map2.put("2", "2");
        map2.put("3", "3");
        map2.compute("4", (k, v) -> k + v);
        map2.forEach((k, v) -> System.out.println(k + "=" + v));
    }
}
