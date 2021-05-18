package com.roadmap.algorithm.leetcode;
//根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// Related Topics 树 深度优先搜索 数组
// 👍 1039 👎 0
import java.util.*;

public class Leetcode105 {
    private final Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeRecur(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTreeRecur(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        //根节点值
        int rootVal = preorder[preLeft];
        //中序数组根节点下标
        int inorderRootIndex = map.get(rootVal);
        int leftSize = inorderRootIndex - inLeft;
        //计算出左子树的前序数组,中序数组
        //前序遍历: 根,左子树,右子树
        //中序遍历: 左子树,根,右子树
        TreeNode root = new TreeNode(rootVal);
        //前序左子树: preLeft+1,preLeft+size
        //中序左子树: inLeft,indexRoot-1
        TreeNode leftTree = buildTreeRecur(preorder, inorder, preLeft + 1, preLeft + leftSize, inLeft, inorderRootIndex - 1);
        //前序右子树: preLeft+size+1,preRight
        //中序右子树: rootIndex+1,inRight
        TreeNode rightTree = buildTreeRecur(preorder, inorder, preLeft + 1 + leftSize, preRight, inorderRootIndex + 1, inRight);
        root.left = leftTree;
        root.right = rightTree;
        return root;
    }
}
