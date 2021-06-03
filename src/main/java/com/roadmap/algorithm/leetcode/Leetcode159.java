package com.roadmap.algorithm.leetcode;

import java.util.Arrays;

public class Leetcode159 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
