package com.roadmap.algorithm.Leetcode;

import com.roadmap.algorithm.Leetcode.MaxArea11;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxArea11Test {

    @Test
    void maxArea() {
        MaxArea11 max = new MaxArea11();
        int i = max.maxArea(new int[]{4, 3, 2, 1, 4});
        assertEquals(i, 16);
    }
}