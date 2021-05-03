package com.roadmap.algorithm.Leetcode;

public class TestUtil {
    public static void printArray(int[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i : arr) {
            builder.append(i)
                    .append("  ");
        }
        System.out.println(builder.toString());
    }
}
