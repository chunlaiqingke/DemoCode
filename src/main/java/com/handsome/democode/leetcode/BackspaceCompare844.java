package com.handsome.democode.leetcode;

public class BackspaceCompare844 {
    public static void main(String[] args) {
        boolean abc = new BackspaceCompare844().backspaceCompare("nzp#o#g", "b#nzp#o#g");
        System.out.println(abc);
    }

    public boolean backspaceCompare(String s, String t) {
        //倒着比较，因为#只会影响前面，不会影响后面
        int sindex = s.length() - 1;
        int tindex = t.length() - 1;
        while(true){
            if(sindex < 0 || tindex < 0) {
                break;
            }
            int scount = 0;
            while(sindex >= 0 && s.charAt(sindex) == '#' || scount > 0){
                if(sindex < 0) {
                    break;
                }
                if(s.charAt(sindex) == '#') {
                    scount ++;
                } else {
                    scount --;
                }

                sindex --;
            }
            int tcount = 0;
            while(tindex >= 0 && t.charAt(tindex) == '#' || tcount > 0 ){
                if(tindex < 0) {
                    break;
                }
                if(t.charAt(tindex) == '#') {
                    tcount ++;
                } else{
                    tcount --;
                }

                tindex --;
            }
            if(sindex < 0 || tindex < 0) {
                break;
            }
            if(s.charAt(sindex) != t.charAt(tindex)) {
                return false;
            }
            sindex --;
            tindex --;
        }
        if(sindex < 0) {
            int tcount = 0;
            while(tindex >= 0 && t.charAt(tindex) == '#' || tcount > 0 ){
                if(tindex < 0) {
                    break;
                }
                if(t.charAt(tindex) == '#') {
                    tcount ++;
                } else{
                    tcount --;
                }

                tindex --;
            }
            return tindex < 0;
        }
        int scount = 0;
        while(sindex >= 0 && s.charAt(sindex) == '#' || scount > 0){
            if(sindex < 0) {
                break;
            }
            if(s.charAt(sindex) == '#') {
                scount ++;
            } else {
                scount --;
            }

            sindex --;
        }
        return sindex < 0;

    }
}
