package com.roadmap.algorithm.leetcode;

//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征：
//
//
// 节点的左子树只包含小于当前节点的数。
// 节点的右子树只包含大于当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
// 示例 1:
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
//
//
// 示例 2:
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
//
// Related Topics 树 深度优先搜索 递归
// 👍 1060 👎 0
public class Leetcode98 {
    long pre = Long.MIN_VALUE;
    long min = Long.MIN_VALUE;
    long max = Long.MAX_VALUE;

    public boolean isValidBST(TreeNode root) {
        //中序遍历思路
        //树空则终止递归,返回成功
        if (root == null) {
            return true;
        }
        //验证左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        //因为是中序遍历,中序遍历是升序排列,所有当前节点一定要大于上个节点
        if (root.val <= pre) {
            return false;
        }
        //替换上个节点为当前节点
        pre = root.val;
        //开始遍历下个节点
        return isValidBST(root.right);
    }

    public boolean isValidBSTV2(TreeNode root) {
        //设计一个函数判断 root.val是否在一定的范围
        //1. 左子树 (min,root.val)
        //2. 右子树 (root.val,max)
        return isValidV2(root, min, max);
    }

    private boolean isValidV2(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidV2(root.left, min, root.val) && isValidV2(root.right, root.val, max);
    }
}
