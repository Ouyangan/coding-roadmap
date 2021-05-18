package com.roadmap.algorithm.leetcode;

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：5
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= n <= 19
//
// Related Topics 树 动态规划
// 👍 1148 👎 0
public class Leetcode96 {
    public int numTrees(int n) {
        //动态规划
        //公式推算过程:
        //(1,n) 假设1<k<n ,则左子树 (1,k-1) 右子树(k+1,n) 根节点为k
        //总数量为:f(k)+=f(k-1)*f(n-k)
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
