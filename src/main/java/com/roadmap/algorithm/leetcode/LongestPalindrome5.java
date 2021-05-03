package com.roadmap.algorithm.leetcode;

//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
// 示例 3：
//
//
//输入：s = "a"
//输出："a"
//
//
// 示例 4：
//
//
//输入：s = "ac"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母（大写和/或小写）组成
//
// Related Topics 字符串 动态规划
// 👍 3580 👎 0
public class LongestPalindrome5 {
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        //最大长度
        int maxLen = 1;
        //最大长度时开始位置
        int maxLeft = 0;
        //记录每轮扩散的长度
        int currlen = 1;
        //选择一个基准点,左右两边同时扩散
        for (int i = 1; i < arr.length; i++) {
            int left = i - 1;
            int right = i + 1;
            //基准点向左扩散
            // aab
            while (left >= 0 && arr[left] == arr[i]) {
                left--;
                currlen++;
            }
            //基准点向右扩散
            //abb
            while (right <= arr.length - 1 && arr[right] == arr[i]) {
                right++;
                currlen++;
            }
            //左右两边同时扩散
            //aba
            while (left >= 0 && right <= arr.length - 1 && arr[left] == arr[right]) {
                left--;
                right++;
                currlen += 2;
            }
            //保存当前最大长度以及位置
            if (currlen > maxLen) {
                maxLen = currlen;
                maxLeft = left;
            }
            //准备下一个基准点遍历,还原长度
            currlen = 1;
        }
        //注意开始位置是真实位置-1,因为while循环执行left--只有在下一轮循环时才能跳出
        return s.substring(maxLeft + 1, maxLeft + maxLen + 1);
    }
}
