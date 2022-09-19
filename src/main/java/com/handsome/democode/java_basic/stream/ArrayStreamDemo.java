package com.handsome.democode.java_basic.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayStreamDemo {

    public static void main(String[] args) {
        List<String> collect = Arrays.stream(new int[]{1, 2, 3}).filter(i -> i > 10).mapToObj(i -> "").collect(Collectors.toList());
        System.out.println(collect);
    }
}
