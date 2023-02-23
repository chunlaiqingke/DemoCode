package com.handsome.democode.leetcode.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int []arr = {9,8,7,6,5,4,3,2,1};
        new QuickSort().smart_sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void smart_sort(int[] arr, int low, int high){
        if(low >= high) {
            return;
        }
        int l = low, r = high;
        //把第一个元素值保存下来，作为参考值
        int pivot = arr[l];
        while(l < r){
            while(l < r && arr[r] >= pivot) {
                r--;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] <= pivot) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;
        smart_sort(arr, low, l - 1);
        smart_sort(arr, l + 1, high);
    }

    /**
     * 愚蠢的写法
     * @param arr
     * @param l
     * @param r
     */
    public void sort(int[] arr, int l, int r){
        if(l >= r) {
            return;
        }
        //以第一个元素作为参考指标
        int center = l;
        int other = r;
        while(center != other){
            if(center < other) {
                if(arr[center] > arr[other]) {
                    swap(arr, center, other);

                    int tmp = center;
                    center = other;
                    other = tmp;

                    other++;
                } else {
                    other--;
                }
            } else {
                if(arr[center] < arr[other]) {
                    swap(arr, center, other);

                    int tmp = center;
                    center = other;
                    other = tmp;

                    other --;
                } else {
                    other ++;
                }
            }
        }
        sort(arr, l, center - 1);
        sort(arr, center + 1, r);
    }

    public void swap(int[] arr, int l, int r){
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
}
