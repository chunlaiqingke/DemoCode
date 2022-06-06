package com.handsome.democode.leetcode;

public class MinSubArrayLen {

    public static void main(String[] args) {
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        int i = minSubArrayLen.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(i);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int result = nums.length;
        while(left <= right && right < nums.length){
            if(sum < target) {
                sum += nums[right++];
            } else {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result;
    }
}
