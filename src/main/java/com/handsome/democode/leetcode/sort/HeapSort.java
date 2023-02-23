package com.handsome.democode.leetcode.sort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
//        sort(arr);
        new HeapSort()._sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public void _sort(int[] arr) {
        for(int i = arr.length - 1; i > 0; i--) {
            //调整完之后，arr[0]就是最大值，把他放到i的位置上；
            _adjustHeap(arr, i);
            swap(arr, 0, i);
        }
    }

    public void _adjustHeap(int[] arr, int cur_length){
        for(int i = cur_length; i >= 0; i--) {
            if(i * 2 + 1 > cur_length) {//没有左子树
                continue;
            }
            if(i * 2 + 2 > cur_length) {//没有右子树
                continue;
            }
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            //调整当前节点和叶子节点的大小关系
            _swap(arr, i, left, right);
        }
    }

    public void _swap(int[] arr, int cur, int left, int right) {
        if(arr[left] >= arr[right]) {
            _swap(arr, cur, left);
        } else {
            _swap(arr, cur, right);
        }
    }

    public void _swap(int[] arr, int father, int son){
        if(arr[father] < arr[son]) {
            swap(arr, father, son);
        }
    }

    //=================================================================//

    public static void sort(int []arr){

        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int []arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for(int k = i*2+1; k < length; k = k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
