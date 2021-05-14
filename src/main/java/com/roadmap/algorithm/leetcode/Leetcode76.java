package com.roadmap.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
// 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 105
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 1151 👎 0

public class Leetcode76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int maxLength = Integer.MAX_VALUE;
        int startIndex = 0;
        while (right < s.length()) {
            Character rc = s.charAt(right);
            if (map.containsKey(rc)) {
                map.put(rc, map.getOrDefault(rc, 0) - 1);
            }
            right++;
            while (match(map)) {
                if (right - left < maxLength) {
                    maxLength = right - left;
                    startIndex = left;
                }
                Character lc = s.charAt(left);
                if (map.containsKey(lc)) {
                    map.put(lc, map.getOrDefault(lc, 0) + 1);
                }
                left++;
            }
        }
        if (maxLength == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(startIndex, startIndex + maxLength);

    }

    private boolean match(Map<Character, Integer> map) {
        for (Integer value : map.values()) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }
}
