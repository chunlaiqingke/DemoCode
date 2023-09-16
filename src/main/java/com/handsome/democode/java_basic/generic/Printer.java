package com.handsome.democode.java_basic.generic;

import java.util.ArrayList;
import java.util.List;

public class Printer {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        new Printer().print(list);
    }

    private void print(List<?> list){
        System.out.println(list);
    }
}
