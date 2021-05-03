package com.roadmap.algorithm.leetcode;

import org.junit.jupiter.api.Test;

class LongestPalindrome5Test {

    @Test
    void longestPalindrome() {
        LongestPalindrome5 l = new LongestPalindrome5();
        String aba = l.longestPalindrome("aba");
        System.out.println(aba);
    }
}