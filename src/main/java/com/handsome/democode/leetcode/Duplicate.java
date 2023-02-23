package com.handsome.democode.leetcode;

import java.util.Arrays;
import java.util.HashSet;

public class Duplicate {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1,2,3,4};
        HashSet<Integer> set = new HashSet<>();

        for(int num : nums) {
            if(!set.add(num)) {
                System.out.println(true);
                break;
            }
        }
        System.out.println(false);


    }
}
