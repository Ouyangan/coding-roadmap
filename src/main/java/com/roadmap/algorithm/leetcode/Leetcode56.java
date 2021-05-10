package com.roadmap.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2：
//
//
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 104
//
// Related Topics 排序 数组
// 👍 921 👎 0
public class Leetcode56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //按左边界排序
        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            //当前区间为第一个区间或者
            //当前区间左边界大于上一个区间的有边界则说明两区间不重叠
            //以上两种情况直接加入即可
            if (list.size() == 0 || list.get(list.size() - 1)[1] < left) {
                list.add(intervals[i]);
            } else {
                //两个区间重叠
                //左区间不用变,因为已经排序了,每次加入合并区间时一定是最小的
                //右区间取 两个区间的最大值即可
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], right);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}
