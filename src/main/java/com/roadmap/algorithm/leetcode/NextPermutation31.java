package com.roadmap.algorithm.leetcode;

//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//
// 必须 原地 修改，只允许使用额外常数空间。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[1,3,2]
//
//
// 示例 2：
//
//
//输入：nums = [3,2,1]
//输出：[1,2,3]
//
//
// 示例 3：
//
//
//输入：nums = [1,1,5]
//输出：[1,5,1]
//
//
// 示例 4：
//
//
//输入：nums = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
//
// Related Topics 数组
// 👍 1085 👎 0
public class NextPermutation31 {
    public void nextPermutation(int[] nums) {
        // 思路是反向查找数组,找到左边最小的数和右边最大的数,最大的数尽可能小
        // 4 5 2 6 3 1 -> 2 3
        //1. 从n,0 反向查询第一个arr[i] < arr[i+1]的数,此时i即为最小的数, 此时(i+1,n)为降序
        //2. 从(i+1,n)反向查询,第一个arr[i]<arr[j],此时j即为最大的数
        //3. 交换arr[i] arr[j],此时(i+1,n)依然为降序,利用双指针交换即可,不必使用排序算法进行排序
        int len = nums.length;
        int left = len - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }
        if (left >= 0) {
            int right = len - 1;
            while (right >= 0 && nums[left] >= nums[right]) {
                right--;
            }
            swap(nums, left, right);
        }
        reverse(nums, left + 1, len - 1);
    }

    private void print(int[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i : arr) {
            builder.append(i)
                    .append("  ");
        }
        System.out.println(builder.toString());
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverse(int[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }

    }
}
