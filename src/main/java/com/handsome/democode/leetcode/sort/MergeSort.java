package com.handsome.democode.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        new MergeSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr){
        split(arr, 0, arr.length-1);
    }

    //先分治，在merge
    public void split(int[] arr, int left, int right){
        if(right <= left) {
            return;
        }
        int mid = (left + right) / 2;
        split(arr, left, mid);
        split(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    /**
     * merge core
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    public void merge(int[] arr, int l, int mid, int r){
        int lbegin = l;
        int lend = mid;
        int rbegin = mid + 1;
        int rend = r;

        int[] tmp = new int[rend - lbegin + 1];
        int index = 0;
        while(lbegin <= lend || rbegin <= rend){
            if(lbegin> lend) {
                tmp[index++] = arr[rbegin++];
                continue;
            }

            if(rbegin> rend) {
                tmp[index++] = arr[lbegin++];
                continue;
            }

            if(arr[lbegin] > arr[rbegin]){
                tmp[index++] = arr[lbegin ++];
            } else {
                tmp[index++] = arr[rbegin ++];
            }
        }

        //把tmp覆盖到arr上

        for(int i = 0; i<tmp.length; i++){
            arr[l+i] = tmp[i];
        }
    }
}
