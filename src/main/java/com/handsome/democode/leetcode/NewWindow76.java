package com.handsome.democode.leetcode;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import java.util.HashMap;
import java.util.Map;

public class NewWindow76 {
    public static void main(String[] args) {
        NewWindow76 wind = new NewWindow76();
        String s = wind.minWindow("cabwefgewcwaefgcf", "cae");
        System.out.println(s);
    }
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> tcount = new HashMap<>();
        for(int i = 0; i < t.length(); i++) {
            int c = tcount.getOrDefault(t.charAt(i), 0);
            tcount.put(t.charAt(i), c+1);
        }
        int sl = 0, sr = 0;
        int resl = sl, resr = sr;
        Map<Character, Integer> subcount = new HashMap<>();
        while(sl < s.length()) {
            boolean check = checkMap(tcount, subcount);
            if(check) {
                resl = sl;
                resr = sr;
                char charL = s.charAt(sl);
                int cl = subcount.get(charL);
                subcount.put(charL, cl-1);
                sl++;
            } else {
                if(sr >= s.length()) {
                    break;
                }
                char charR = s.charAt(sr);
                int cR = subcount.getOrDefault(charR, 0);
                subcount.put(charR, cR + 1);
                sr++;
            }
        }
        return s.substring(resl, resr);
    }

    public boolean checkMap(Map<Character, Integer> tcount, Map<Character, Integer> subcount) {
        for(Character k : tcount.keySet()) {
            int ct = tcount.get(k);
            int cs = subcount.getOrDefault(k, 0);
            if(cs < ct) {
                return false;
            }
        }
        return true;
    }
}
