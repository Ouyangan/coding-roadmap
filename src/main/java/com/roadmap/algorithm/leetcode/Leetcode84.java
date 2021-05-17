package com.roadmap.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
//
//
//
//
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
//
//
//
//
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
//
//
//
// 示例:
//
// 输入: [2,1,5,6,2,3]
//输出: 10
// Related Topics 栈 数组
// 👍 1357 👎 0
public class Leetcode84 {
    public int largestRectangleArea(int[] heights) {
        int[] temp = new int[heights.length + 2];
        System.arraycopy(heights, 0, temp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < temp.length; i++) {
            //如果当前i高度比栈顶元素小,则可以确定栈顶元素的面积
            while (!stack.isEmpty() && temp[i] < temp[stack.peek()]) {
                int h = temp[stack.pop()];
                area = Math.max(area, (i - stack.peek() - 1) * h);
                printStack(stack,heights);
            }
            stack.push(i);
        }
        return area;
    }

    private void printStack(Deque<Integer> stack, int[] heights) {
        for (Integer integer : stack) {
            System.out.print(integer+":"+heights[integer] + ",");
        }
        System.out.println();
    }

    public int largestRectangleAreaV2(int[] heights) {
        //暴力解法
        int len = heights.length;
        int max = 0;
        //当前下标分别从左右两边扩散
        for (int i = 0; i < len; i++) {
            //对于左边,找到大于等于当前高度最左下标
            int left = i;
            while (left > 0 && heights[left - 1] >= heights[i]) {
                left--;
            }
            //对于右边,找到大于等于当前高度最优下标
            int right = i;
            //这里len-1,是因为最右只能是len
            while (right < len - 1 && heights[right + 1] >= heights[i]) {
                right++;
            }
            int width = right - left + 1;
            max = Math.max(max, width * heights[i]);
        }
        return max;
    }

    //暴力枚举
    public int largestRectangleAreaV1(int[] heights) {
        //暴力解法
        int len = heights.length;
        int max = 0;
        for (int left = 0; left < len; left++) {
            int minHeight = Integer.MAX_VALUE;
            for (int right = left; right < len; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                max = Math.max(max, (right - left + 1) * minHeight);
            }
        }
        return max;
    }
}
