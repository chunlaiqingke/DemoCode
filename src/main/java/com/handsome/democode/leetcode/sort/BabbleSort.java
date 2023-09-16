package com.handsome.democode.leetcode.sort;

import java.util.Arrays;

public class BabbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5,23,6,3,7,8};
        new BabbleSort().sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    public void sort(int[] arr){
        for(int i = arr.length -1; i > 0; i--) {
            for(int j = 0; j<i; j++){
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
