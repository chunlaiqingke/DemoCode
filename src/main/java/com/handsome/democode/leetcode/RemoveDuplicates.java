package com.handsome.democode.leetcode;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,1,2,3,3};
        int i = new RemoveDuplicates().removeDuplicates(nums);
    }
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int cur_num = nums[0];
        int res = 1;
        int index = 1;
        while(index < nums.length){
            if(cur_num == nums[index]) {
                if(count < 2) {
                    res ++;
                    count ++;
                }
            } else {
                nums[res] = nums[index];
                cur_num = nums[index];
                res ++;
                count = 1;
            }
            index ++;
        }
        return res;
    }
}
