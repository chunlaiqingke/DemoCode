package com.handsome.democode.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring sub = new LengthOfLongestSubstring();
        int tmmzuxt = sub.lengthOfLongestSubstring("tmmzuxt");
        System.out.println(tmmzuxt);
    }

    public int lengthOfLongestSubstring(String s) {
        /**
         用2个指针前后跑，如果出现相同的，后面的指针往前，如果不相同，前面的指针往前，并计算长度
         */
        int res = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int right = 0; right < s.length(); right++){
            char ch = s.charAt(right);
            if(map.containsKey(ch)) {
                int index = map.get(ch);
                map.put(ch, right);
                left = index + 1;
            } else {
                map.put(ch, right);
                res = Math.max(res, right - left + 1);
            }
        }
        return res;
    }
}
