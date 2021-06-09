package com.roadmap.algorithm.leetcode;
//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
//
//
//
// 示例 1：
//
//
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
//
//
// 示例 2：
//
//
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
//
//
// 示例 3：
//
//
//输入：matrix = [["0"]]
//输出：0
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 300
// matrix[i][j] 为 '0' 或 '1'
//
// Related Topics 动态规划
// 👍 783 👎 0
public class Leetcode221 {
    //遍历二维矩阵,每次遇到1,则将该元素当做正方形左上角
    //确定正方形左上角后,尝试计算最大的边长
    //校验正方形每个元素是否为1
    public int maximalSquare(char[][] matrix) {
        int side = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return side;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    side = Math.max(1, side);
                    int currentSize = Math.min(row - i, column - j);
                    //从标点逐渐拓展边界
                    //遇到'0'终止
                    for (int k = 1; k < currentSize; k++) {
                        boolean flag = true;
                        //校验右下角
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        //此时已经定位右下角为1
                        //开始校验其他位置是否为1
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + m][j + k] == '0' || matrix[i + k][j + m] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        //取最大边长
                        if (flag) {
                            side = Math.max(side, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return side * side;
    }

    public int maximalSquareV2(char[][] matrix) {
        int side = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return side;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] dp = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    side = Math.max(side, dp[i][j]);
                }
            }
        }
        return side * side;
    }
}
