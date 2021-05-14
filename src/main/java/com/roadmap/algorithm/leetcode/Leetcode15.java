package com.roadmap.algorithm.leetcode;

import java.util.*;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：[]
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 3000
// -105 <= nums[i] <= 105
//
// Related Topics 数组 双指针
// 👍 3286 👎 0
public class Leetcode15 {
    private final Set<List<Integer>> set = new HashSet<>();

    //双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }
        //排序
        Arrays.sort(nums);
        //遍历数组,定义基准点
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //左指针
            int left = i + 1;
            //右指针
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //左指针右移,相同时继续右移去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    //右指针左移,相同时继续左移去重
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return list;
    }

    //回溯
    public List<List<Integer>> threeSum2(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.print(num + ",");
        }
        System.out.println("=======");
        boolean[] used = new boolean[nums.length];
        backTrace(nums, 0, k, 0, new ArrayList<>(), result, used);
        return result;
    }

    private void backTrace(int[] arr, int index, int k, int sum, List<Integer> path, List<List<Integer>> result, boolean[] used) {
        //k减为0,并且sum==0时添加进结果集
        if (k == 0) {
            if (sum == 0) {
                ArrayList<Integer> temp = new ArrayList<>(path);
                Collections.sort(temp);
                if (!result.contains(temp)) {
                    result.add(new ArrayList<>(path));
                }
            }
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            if (used[i]) {
                continue;
            }
            if (sum > 0) {
                break;
            }
            used[i] = true;
            path.add(arr[i]);
            print(path);
            backTrace(arr, index + 1, k - 1, arr[i] + sum, path, result, used);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    private void print(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (Integer integer : list) {
            builder.append(integer).append(",");
        }
        System.out.println("==>" + builder.toString());
    }
}
