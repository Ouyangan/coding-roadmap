package com.roadmap.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的数字可以无限制重复被选取。
//
// 说明：
//
//
// 所有数字（包括 target）都是正整数。
// 解集不能包含重复的组合。
//
//
// 示例 1：
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
//
//
// 示例 2：
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//]
//
//
//
// 提示：
//
//
// 1 <= candidates.length <= 30
// 1 <= candidates[i] <= 200
// candidate 中的每个元素都是独一无二的。
// 1 <= target <= 500
//
// Related Topics 数组 回溯算法
// 👍 1325 👎 0
public class Leetcode39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //回溯
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backTrace(candidates, target, 0, path, result);
        return result;
    }

    private void backTrace(int[] arr, int sum, int index, List<Integer> path, List<List<Integer>> result) {
        //剩余量为0
        if (sum == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        //小于0剪枝
        if (sum < 0) {
            return;
        }
        //index开始遍历
        for (int i = index; i <= arr.length - 1; i++) {
            path.add(arr[i]);
            //注意这里从i开始,因为可以重复选取
            backTrace(arr, sum - arr[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }
}
