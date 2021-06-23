package com.roadmap.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class Leetcode239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //结果数组
        int[] res = new int[nums.length - k + 1];
        //双向队列,从大到小排列
        Deque<Integer> deque = new LinkedList<>();
        for (int right = 0; right < nums.length; right++) {
            //队列非空
            //当前元素大于队尾元素,则移除队尾,直到当前队列为空或者当前元素小于队尾元素
            while (!deque.isEmpty() && nums[right] >= nums[deque.peekLast()]) {
                deque.removeLast();
            }
            //添加下标到队列
            deque.addLast(right);
            //左边界
            int left = right - k + 1;
            //如果队首元素小于左边界时,说明已经出界,将其移除去
            if (deque.peekFirst() < left) {
                deque.removeFirst();
            }
            //窗口形成时,添加结果集
            if (right + 1 >= k) {
                res[left] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
