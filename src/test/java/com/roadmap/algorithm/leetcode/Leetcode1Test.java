package com.roadmap.algorithm.leetcode;

import org.junit.jupiter.api.Test;

class Leetcode1Test {
    @Test
    public void twoSumTest() {
        Leetcode1 leetcode1 = new Leetcode1();
        int[] ints = leetcode1.twoSum(new int[]{1, 2, 3, 5}, 8);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}