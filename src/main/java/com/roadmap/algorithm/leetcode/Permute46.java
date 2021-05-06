package com.roadmap.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 1328 👎 0
public class Permute46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backTrace(nums, 0, path, result, used);
        return result;
    }

    private void backTrace(int[] arr, int index, List<Integer> path, List<List<Integer>> result, boolean[] used) {
        //数组长度达到上限
        if (index == arr.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        //每次从0开始
        for (int i = 0; i < arr.length; i++) {
            //记住已选定的元素
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(arr[i]);
            backTrace(arr, index + 1, path, result, used);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
