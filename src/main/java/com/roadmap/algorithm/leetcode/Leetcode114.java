package com.roadmap.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

//给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
//
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。
//
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 2000] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
// Related Topics 树 深度优先搜索
// 👍 798 👎 0
public class Leetcode114 {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode curr = list.get(i);
            pre.left = null;
            pre.right = curr;
        }
    }

    private void preOrder(TreeNode node, List<TreeNode> list) {
        if (node != null) {
            list.add(node);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }
}
