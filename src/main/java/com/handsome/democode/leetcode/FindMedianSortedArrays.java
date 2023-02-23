package com.handsome.democode.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {2, 3};

        double medianSortedArrays = new FindMedianSortedArrays().findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {



        Deque<Integer> mid = new ArrayDeque<>();

        int l = nums1.length + nums2.length;

        boolean parity = l % 2 == 1;

        int index1 = 0, index2 = 0;

        int count = 0;
        while(index1 < nums1.length || index2 < nums2.length){

            if(index1 >= nums1.length) {
                mid.addFirst(nums2[index2++]);
            } else if(index2 >= nums2.length){
                mid.addFirst(nums1[index1++]);
            }else if(nums1[index1] < nums2[index2]) {
                mid.addFirst(nums1[index1++]);
            } else {
                mid.addFirst(nums2[index2++]);
            }

            if(mid.size() > 2) {
                mid.removeLast();
                count++;
            }

            if(parity) {
                if(count == l / 2) {
                    return mid.getLast();
                }
            } else {
                if(count == l / 2 - 1) {
                    return (mid.getFirst() + mid.getLast()) / 2.0;
                }
            }
        }
        return 0;
    }
}
