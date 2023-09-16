package com.handsome.democode.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class SearchInsert35 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        int target = 2;
        int i = new SearchInsert35().searchInsert(nums, target);
        System.out.println(i);
    }

    public int searchInsert(int[] nums, int target) {
        return searchInsert(nums, target, 0, nums.length - 1);
    }

    public int searchInsert(int[] nums, int target, int left, int right) {
        if(left > right) {

        }
        int mid = (left + right) / 2;
        if(nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            if(target < nums[mid-1]){
                return mid-1;
            }
            return searchInsert(nums, target, left, mid - 1);
        } else if (nums[mid] < target) {
            if(target > nums[mid+1]){
                return mid+1;
            }
            return searchInsert(nums, target, mid + 1, right);
        } else {
            return mid;
        }
    }
}
