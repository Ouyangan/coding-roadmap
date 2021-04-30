package com.roadmap.algorithm.Leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis22 {
    public List<String> generateParenthesis(int n) {
        //回溯
        List<String> result = new ArrayList<>();
        backTrace(n, n, new StringBuilder(), result);
        return result;
    }

    private void backTrace(int left, int right, StringBuilder builder, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(builder.toString());
            return;
        }
        // (()))(
        if (left > right) {
            return;
        }
        if (left > 0) {
            builder.append("(");
            backTrace(left - 1, right, builder, result);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (right > 0) {
            builder.append(")");
            backTrace(left, right - 1, builder, result);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
