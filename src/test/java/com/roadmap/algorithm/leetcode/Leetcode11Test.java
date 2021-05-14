package com.roadmap.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Leetcode11Test {

    @Test
    void maxArea() {
        Leetcode11 max = new Leetcode11();
        int i = max.maxArea(new int[]{4, 3, 2, 1, 4});
        assertEquals(i, 16);
    }
}