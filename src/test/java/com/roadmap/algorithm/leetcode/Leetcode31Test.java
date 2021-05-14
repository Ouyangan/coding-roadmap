package com.roadmap.algorithm.leetcode;

import org.junit.jupiter.api.Test;

class Leetcode31Test {

    @Test
    void nextPermutation() {
        Leetcode31 next = new Leetcode31();
        int[] arr = {3, 2, 1};
        TestUtil.printArray(arr);
        next.nextPermutation(arr);
        TestUtil.printArray(arr);
    }
}