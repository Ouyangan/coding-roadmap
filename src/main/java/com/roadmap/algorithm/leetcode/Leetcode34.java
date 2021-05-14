package com.roadmap.algorithm.leetcode;

public class Leetcode34 {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            } else {
                return new int[]{-1, -1};
            }
        }
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        //这里主要注意left=right,比如[1, 4] 4
        while (left <= right) {
            //二分法
            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                System.out.println(mid);
                //中心扩散
                int start = mid;
                int end = mid;
                //找左边为位置,注意此时需要start>0防止0时越界
                while (start > 0 && nums[start] == nums[start - 1]) {
                    start--;
                }
                //找右边位置
                while (end < len - 1 && nums[end] == nums[end + 1]) {
                    end++;
                }
                return new int[]{start, end};
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }
}
