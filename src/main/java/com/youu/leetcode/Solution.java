package com.youu.leetcode;

import javax.xml.stream.events.Characters;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int p = (begin + end) >> 1;
            int n = nums[p];
            if (n == target) {
                return p;
            } else if (n < target) {//target 在后半段
                begin = p + 1;
            } else {//target 在前半段
                end = p - 1;
            }
        }

        return -1;
    }

    public int firstBadVersion(int n) {
        int low = 0, high = n;
        while (low < high) {
            int mid = ((high - low) >> 1) + low;
            if (isBadVersion(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean isBadVersion(int d) {
        if (d >= 1702766719) {
            return true;
        }
        return false;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int p = (m + n) / 2;
        int j = 0, k = 0;
        int v1 = 0, v2 = 0;
        for (int i = 0; i <= p; i++) {
            v1 = v2;
            //v2=min(nums1,nums2)
            if (j < m && k < n) {
                if (nums1[j] <= nums2[k]) {
                    v2 = nums1[j++];
                } else {
                    v2 = nums2[k++];
                }
            } else if (j == m) {
                v2 = nums2[k++];
            } else if (k == n) {
                v2 = nums1[j++];
            } else {
                System.err.println("error occur...");
            }

        }

        return ((m + n) & 1) == 0 ? ((double) (v1 + v2)) / 2 : v2;
    }

    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            findMedianSortedArraysV2(nums2, nums1);
        }

        int left = 0, right = m;
        int v1 = 0, v2 = 0;

        while (left < right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

        }

        return ((m + n) & 1) == 0 ? ((double) (v1 + v2)) / 2 : v2;
    }

    public String longestSubstring(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        Map<Character, Integer> dic = new HashMap<>();
        int left = 0, right = 0;
        int p = 0;
        for (int i = 0; i < length; i++) {
            char c = chars[i];
            if (dic.get(c) != null) {
                if (right - left < i - 1 - p) {
                    left = p;
                    right = i - 1;
                }
                p = dic.get(c) + 1;
            } else {
                //
                if (right - left < i - p) {
                    left = p;
                    right = i;
                }
            }
            dic.put(c, i);
        }
        return new String(chars, left, right - left + 1);
    }

    public String longestPalindrome(String s) {
        char[] c = s.toCharArray();
        int length = c.length;
        int left = 0, right = 0;
        for (int i = 0; i < length; i++) {
            int p = 0;
            while (true) {
                p++;
                if (i - p >= 0 && i + p < length && c[i - p] == c[i + p]) {
                    if (right - left + 1 < 2 * p + 1) {
                        left = i - p;
                        right = i + p;
                    }
                } else {
                    break;
                }
            }
            p = 0;
            while (true) {
                p++;
                if (i + 1 - p >= 0 && i + p < length && c[i + 1 - p] == c[i + p]) {
                    if (right - left + 1 < 2 * p) {
                        left = i + 1 - p;
                        right = i + p;
                    }
                } else {
                    break;
                }
            }
        }
        return new String(c, left, right - left + 1);
    }

    public String convert(String s, int numRows) {

        char[][] data = new char[numRows][];
        for (int i = 0; i < numRows; i++) {
            data[i] = new char[s.length()];
        }
        int i = 0, m = 0/*行号*/, n = 0/*列号*/;
        while (i < s.length()) {
            while (m < numRows && i < s.length()) {
                data[m++][n] = s.charAt(i++);
            }
            m--;//numRows-1
            m--;
            n++;
            while (m > 0 && i < s.length()) {
                data[m--][n++] = s.charAt(i++);
            }
            if (m < 0) {
                m = 0;
            }
        }

        String result = new String();
        for (int j = 0; j < data.length; j++) {
            char[] row = data[j];
            for (int k = 0; k < n; k++) {
                if (row[k] != 0) {
                    result += row[k];
                }
            }
        }
        return result;
    }

    public String convert2(String s, int numRows) {
        int length = s.length();
        int m = numRows * 2 - 2;
        int numColumns = length / m * (numRows - 1) + (length % m < numRows ? 1 : (length % m) - numRows + 1);
        char[][] data = new char[numRows][numColumns];

        int i = 0, r = 0/*行号*/, c = 0/*列号*/;
        while (i < length) {
            data[r][c] = s.charAt(i);
            if (i % m < numRows - 1) {//向下
                r++;
            } else {//右上
                r--;
                c++;
            }
            i++;
        }

        String result = new String();
        for (int j = 0; j < data.length; j++) {
            char[] row = data[j];
            for (int k = 0; k < row.length; k++) {
                if (row[k] != 0) {
                    result += row[k];
                }
            }
        }
        return result;
    }

    public int reverse(int x) {
        int r = 0;
        while (x != 0) {
            if (r > Integer.MAX_VALUE / 10 || r < Integer.MIN_VALUE / 10) {
                return 0;
            }
            r = r * 10;
            r += x % 10;
            x = x / 10;
        }
        return r;
    }

    public int myAtoi(String s) {
        int length = s.length();
        int r = 0, b = '0';
        byte sign = 0;
        for (int i = 0; i < length; i++) {
            if (r == 0 && sign == 0 && (s.charAt(i) == ' ' || s.charAt(i) == '0' || s.charAt(i) == '+'
                || s.charAt(i) == '-')) {
                if (s.charAt(i) == '+' || s.charAt(i) == '0') {
                    sign = 1;
                }
                if (s.charAt(i) == '-') {
                    sign = -1;
                }
                continue;
            }
            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                break;
            }
            if (sign >= 0) {
                if (multiplyOverflow(r, 10) || addOverflow(r * 10, (s.charAt(i) - b))) {
                    r = Integer.MAX_VALUE;
                } else {
                    r = r * 10 + (s.charAt(i) - b);
                }
            } else {
                int t = r * 10 - (s.charAt(i) - b);
                if (t <= r && (t + (s.charAt(i) - b)) / 10 == r) {
                    r = t;
                } else {
                    r = Integer.MIN_VALUE;
                }
            }
        }
        return r;
    }

    public boolean addOverflow(int x, int y) {
        int r = x + y;
        return ((x ^ r) & (y ^ r)) < 0;
    }

    public boolean multiplyOverflow(int x, int y) {
        return (x == 0 || y == 0) ? false :
            (x ^ y) > 0 ? ((x & y) > 0) ? (Integer.MAX_VALUE / x < y && Integer.MAX_VALUE / y < x) :
                (Integer.MAX_VALUE / x > y && Integer.MAX_VALUE / y > x) :
                x < 0 ? Integer.MIN_VALUE / x < y : Integer.MIN_VALUE / y < x;
    }

}
