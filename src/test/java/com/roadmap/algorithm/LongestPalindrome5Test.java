package com.roadmap.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestPalindrome5Test {

    @Test
    void longestPalindrome() {
        LongestPalindrome5 l = new LongestPalindrome5();
        String aba = l.longestPalindrome("aba");
        System.out.println(aba);
    }
}