package com.stc.task;
import java.util.Stack;

public class ReverseSubstrings {

    public static String reverseSubstrings(String str) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder(str);

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (!stack.isEmpty()) {
                    int start = stack.pop();
                    reverseSubstring(result, start + 1, i - 1);
                }
            }
        }

        return result.toString();
    }

    private static void reverseSubstring(StringBuilder str, int start, int end) {
        while (start < end) {
            char temp = str.charAt(start);
            str.setCharAt(start, str.charAt(end));
            str.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseSubstrings("abd(jnb)asdf"));  // abd(bnj)asdf
        System.out.println(reverseSubstrings("abdjnbasdf"));  // abdjnbasdf
        System.out.println(reverseSubstrings("dd(df)a(ghhh)"));  // dd(fd)a(hhhg)
        System.out.println(reverseSubstrings("dd((df))a(ghhh)"));  // dd(fd)a(hhhg)

    }
    
}
