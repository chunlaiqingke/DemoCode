package com.handsome.democode.leetcode;

public class TotalFruit904 {

    public static void main(String[] args) {
        int[] fruit = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        int i = new TotalFruit904().totalFruit(fruit);
        System.out.println(i);
    }

    public int totalFruit(int[] fruits) {
        //使用双指针
        //map保存篮子里的索引
        int[] lanzi = new int[]{-1, -1};//记下篮子中的type
        int[] maxIndex = new int[]{-1, -1};//记下篮子中对应类型的最大索引
        int l = 0, r = 0;
        int res = 0;
        while(r < fruits.length){
            if(fruits[r] == lanzi[0] || lanzi[0] == -1) {
                lanzi[0] = fruits[r];
                maxIndex[0] = r;

                res = Math.max(res, r - l + 1);
                r++;
            } else if (fruits[r] == lanzi[1] || lanzi[1] == -1) {
                lanzi[1] = fruits[r];
                maxIndex[1] = r;

                res = Math.max(res, r - l + 1);
                r++;
            } else {
                l = Math.min(maxIndex[0], maxIndex[1]);
                if(l == maxIndex[0]) {
                    maxIndex[0] = r;
                    lanzi[0] = fruits[r];
                } else {
                    maxIndex[1] = r;
                    lanzi[1] = fruits[r];
                }
                l = l + 1;
                r++;
            }
        }
        return res;
    }
}
