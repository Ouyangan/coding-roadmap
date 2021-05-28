package com.roadmap.algorithm.leetcode;
//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
//
//
// 示例 1:
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
//
//
// 示例 2:
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
// Related Topics 数组 动态规划
// 👍 1119 👎 0
public class Leetcode152 {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int curMax = nums[0];
        int curMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            int curr = nums[i];
            if (curr < 0) {
                int temp = curMax;
                curMax = curMin;
                curMin = temp;
            }
            curMax = Math.max(curMax * curr, curr);
            curMin = Math.min(curMin * curr, curr);
            max = Math.max(max, curMax);

        }
        return max;
    }

    public int maxProductV2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        for (int left = 0; left <= len - 1; left++) {
            int curr = left;
            int res = 1;
            while (curr <= len - 1) {
                res = res * nums[curr];
                max = Math.max(max, res);
                curr++;
            }
        }
        return max;
    }
}
