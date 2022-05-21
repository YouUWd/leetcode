package com.youu.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void search() {
        Assert.assertEquals(0, solution.search(new int[] {1}, 1));
        Assert.assertEquals(-1, solution.search(new int[] {1}, 2));

    }

    @Test
    public void firstBadVersion() {
        Assert.assertEquals(1702766719, solution.firstBadVersion(2126753390));
        long l = 281472964516669L;
        int i = (int) l;

        System.out.println(i);
    }

    @Test
    public void testFindMedianSortedArrays() {
        Assert.assertEquals(2, solution.findMedianSortedArrays(new int[] {1, 3}, new int[] {2}), 0);
        Assert.assertEquals(2.5, solution.findMedianSortedArrays(new int[] {1, 3}, new int[] {2, 4}), 0);
        Assert.assertEquals(2.5, solution.findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}), 0);
    }

    @Test
    public void testLongestPalindrome() {
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome("cbbd"));
        System.out.println(solution.longestPalindrome("abbbbba"));
    }

    @Test
    public void convert() {
        Assert.assertEquals("PAHNAPLSIIGYIR", solution.convert2("PAYPALISHIRING", 3));
        Assert.assertEquals("PINALSIGYAHRPI", solution.convert2("PAYPALISHIRING", 4));
//        Assert.assertEquals("A", solution.convert("A", 1));
//        Assert.assertEquals("AB", solution.convert("AB", 1));
    }

    @Test
    public void reverse() {
        System.out.println(solution.reverse(321));
        System.out.println(solution.reverse(-321));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(solution.reverse(Integer.MAX_VALUE - 10));

        System.out.println(Integer.MIN_VALUE);
        System.out.println(solution.reverse(Integer.MIN_VALUE));
        System.out.println(solution.reverse(2147483647));
        System.out.println(solution.reverse(1534236469));
    }

    @Test
    public void myAtoi() {

        System.out.println(solution.myAtoi("20000000000000000000"));

    }

    static int MAX = Integer.MAX_VALUE;
    static int MIN = Integer.MIN_VALUE;

    @Test
    public void test() {
        int i = MIN;
        int j = -10;
        int r = i * j;
        System.out.println(r);
        if ((i ^ j) > 0) {
            System.out.println((i & j) < 0 ? MAX / i <= j && MAX / j <= i : MAX / i >= j);
        } else {
            boolean b = i < 0 ? MIN / i >= j : MIN / j >= i;
            System.out.println(b);
        }
    }

    @Test
    public void multiplyOverflow() {
        System.out.println(-10 * Integer.MAX_VALUE);
        System.out.println(-10 * Integer.MIN_VALUE);
//        System.out.println(solution.multiplyOverflow(Integer.MAX_VALUE, 10));
//        System.out.println(solution.multiplyOverflow(Integer.MAX_VALUE, -10));
//        System.out.println(solution.multiplyOverflow(Integer.MIN_VALUE, -10));
        System.out.println(solution.multiplyOverflow(2000000000, 10));
        System.out.println(2000000000 * 10);
        System.out.println(Integer.MAX_VALUE / 2000000000);
        System.out.println(Integer.MAX_VALUE / 10);
        int x = 2000000000, y = 10;
        System.out.println((Integer.MAX_VALUE / x < y && Integer.MAX_VALUE / y < x));
    }
}