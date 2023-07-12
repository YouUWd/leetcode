package com.youu.leetcode.algorithms;

public class PalindromeNumber9 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int i = 0;
        int[] data = new int[10];
        int o = x;
        while (o > 0) {
            data[i++] = o % 10;
            o = o / 10;
        }
        int j = 0;
        int r = 0;
        while (j < i) {
            r = r * 10 + data[j++];
        }

        return x == r;
    }

    public static void main(String[] args) {
        PalindromeNumber9 p = new PalindromeNumber9();
        System.out.println(p.isPalindrome(121));
        System.out.println(p.isPalindrome(-121));
        System.out.println(p.isPalindrome(10));
    }

}
