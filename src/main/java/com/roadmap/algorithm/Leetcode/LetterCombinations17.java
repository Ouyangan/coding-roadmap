package com.roadmap.algorithm.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
// Related Topics 深度优先搜索 递归 字符串 回溯算法
// 👍 1284 👎 0
public class LetterCombinations17 {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backTrace(digits, 0, createMap(), new StringBuilder(), result);
        return result;
    }

    private void backTrace(String digits, int index, Map<Character, String> map, StringBuilder builder, List<String> result) {
        if (index == digits.length()) {
            result.add(builder.toString());
            return;
        }
        char ch = digits.charAt(index);
        String str = map.get(ch);
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            builder.append(arr[i]);
            backTrace(digits, index + 1, map, builder, result);
            builder.deleteCharAt(builder.length() - 1);
        }

    }

    private Map<Character, String> createMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }
}
