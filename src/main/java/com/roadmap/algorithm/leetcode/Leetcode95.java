package com.roadmap.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
//
//
// Related Topics 树 动态规划
// 👍 874 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Leetcode95 {
    public List<TreeNode> generateTrees(int n) {
        return gen(1, n);
    }

    private List<TreeNode> gen(int left, int right) {
        List<TreeNode> list = new ArrayList<>();
        if (left > right) {
            list.add(null);
            return list;
        }
        for (int i = left; i <= right; i++) {
            //左子树
            List<TreeNode> leftTrees = gen(left, i - 1);
            //右子树
            List<TreeNode> rightTrees = gen(i + 1, right);
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i, leftTree, rightTree);
                    list.add(root);
                }
            }
        }
        return list;
    }
}
