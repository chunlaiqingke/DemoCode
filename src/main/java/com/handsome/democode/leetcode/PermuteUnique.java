package com.handsome.democode.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PermuteUnique {

    public static void main(String[] args) {
        List<List<Integer>> lists = new PermuteUnique().permuteUnique(new int[]{3, 3, 0, 3});
        System.out.println(lists);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        //什么时候剪枝，已选中的元素相同
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(res, nums, used, path, 0, nums.length);
        return res;
    }

    public void dfs(List<List<Integer>> res
            , int[] nums
            , boolean[] used
            , Deque<Integer> path
            , int depth
            , int length) {
        if(depth == length){
            res.add(new ArrayList<>(path));
        }

        for(int i = 0; i < nums.length; i++) {
            if(used[i]) {
                continue;
            }

            //如果前一个元素使用了，则当前元素不计入
            if(i > 0 && nums[i] == nums[i - 1] && used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);

            used[i] = true;

            dfs(res, nums, used, path, depth+1, length);

            path.pollLast();

            used[i] = false;
        }

    }
}
