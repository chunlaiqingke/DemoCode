package com.handsome.democode.leetcode;

import java.util.Stack;

public class Calc {

    public static void main(String[] args) {
        int calculate = new Calc().calculate(" 3/2 ");
        System.out.println(calculate);
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        String num = "";
        String currentOp = "+";
        int res = 0;
        int length = s.length();
        int i = 0;
        while(i < length) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                num += ch;
            } else if (ch == ' ') {
                i++;
                continue;
            }
            if(!Character.isDigit(ch) || i == length - 1) {
                switch(currentOp){
                    case "+":
                        stack.push(Integer.parseInt(num));
                        break;
                    case "-":
                        stack.push(-Integer.parseInt(num));
                        break;
                    case "*":
                        stack.push(stack.pop() * Integer.parseInt(num) );
                        break;
                    case "/":
                        stack.push(stack.pop() / Integer.parseInt(num));
                        break;
                    default: break;
                }
                currentOp = ch + "";
                num = "";
            }
            i++;
        }
        for(int j : stack) {
            res += j;
        }
        return res;
    }
}

