package com.handsome.democode.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ContainsNearbyDuplicate {

    public static void main(String[] args) {

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();

        for(int i = 0; i<nums.length; i++){
            LinkedList<Integer> list = map.getOrDefault(nums[i], new LinkedList<>());
            list.addFirst(i);
            if(list.size() > 1 && Math.abs(list.get(0) - list.get(1)) <= k) {
                return true;
            }
        }
        return false;
    }
}
