package com.handsome.democode.leetcode;

public class MyAtoI {

    public static void main(String[] args) {
        int i = new MyAtoI().myAtoi("+-12");
        System.out.println(i);
    }

    public int myAtoi(String s) {
        char[] chs = s.toCharArray();
        int res = 0;
        char minus = ' ';
        for (Character ch : chs) {
            if (Character.isDigit(ch) || ch == '-') {
                //第一位
                if (res == 0) {
                    if (ch == '-') {
                        minus = '-';
                    } else if (ch != '0') {
                        res = Character.getNumericValue(ch);
                    }
                } else {
                    if (Character.isDigit(ch)) {
                        int num = Character.getNumericValue(ch);
                        if (minus != '-' && (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < num)) {
                            return Integer.MAX_VALUE;
                        } else if (minus == '-' && (res > -(Integer.MIN_VALUE / 10) || res == -(Integer.MIN_VALUE / 10) && Integer.MIN_VALUE % 10 < num)) {
                            return Integer.MIN_VALUE;
                        }
                        res = res * 10 + num;
                    } else {
                        return minus == '-' ? -res : res;
                    }
                }
            } else {
                if (res == 0) {
                    if (ch != ' ' && ch != '+') {
                        return minus == '-' ? -res : res;
                    }
                } else {
                    if (ch == '.') {
                        return minus == '-' ? -res : res;
                    }
                }
            }
        }

        return minus == '-' ? -res : res;
    }
}
