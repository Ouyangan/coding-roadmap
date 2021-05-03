package com.roadmap.algorithm.leetcode;

import org.junit.jupiter.api.Test;

class NextPermutation31Test {

    @Test
    void nextPermutation() {
        NextPermutation31 next = new NextPermutation31();
        int[] arr = {3, 2, 1};
        TestUtil.printArray(arr);
        next.nextPermutation(arr);
        TestUtil.printArray(arr);
    }
}