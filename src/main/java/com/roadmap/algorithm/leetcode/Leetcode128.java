package com.roadmap.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;
//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
//
//
// 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
//
//
//
// 示例 1：
//
//
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
//
// 示例 2：
//
//
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 104
// -109 <= nums[i] <= 109
//
// Related Topics 并查集 数组
// 👍 768 👎 0
public class Leetcode128 {
    Set<Integer> set = new HashSet<>();

    public int longestConsecutive(int[] nums) {
        int max = 0;
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            //如果set中不包含num-1则需计算当前num为起点连续数组的最大长度
            //如果包含num-1,则无需计算,等到遍历到num-1时再计算
            if (!set.contains(num - 1)) {
                int currNum = num;
                int currMax = 1;
                while (set.contains(currNum + 1)) {
                    currMax++;
                    currNum++;
                }
                max = Math.max(max, currMax);
            }
        }
        return max;
    }
}
