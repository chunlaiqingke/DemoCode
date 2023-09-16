package com.handsome.democode.leetcode.sort;

import java.util.Arrays;

public class QuickSort_MY {

    public static void main(String[] args) {
        int[] arr = new int[]{5,23,6,3,7,8};
        new QuickSort_MY().quickSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    public void quickSort(int[] originArr){
        quickSort(originArr, 0, originArr.length -1);
    }

    //递归
    private void quickSort(int[] arr, int start, int end){
        if(start >= end) {
            return;
        }
        int s = start, e = end;
        int pivot = arr[start++];
        boolean dir = false;
        while (start < end) {
            if(!dir) {
                if(arr[end] < pivot) {
                    arr[start] = arr[end];
                    dir = true;
                } else {
                    end --;
                }
            } else {
                if(arr[start] > pivot) {
                    arr[end] = arr[start];
                    dir = false;
                } else {
                    start ++;
                }
            }
        }
        quickSort(arr, s, end-1);
        quickSort(arr, end+1, e);
    }
}
