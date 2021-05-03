package com.roadmap.algorithm.leetcode;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

class ThreeSum15Test {

    @Test
    void threeSum() {
    }

    @Test
    void threeSum2() {
        ThreeSum15 threeSum15 = new ThreeSum15();
        List<List<Integer>> lists = threeSum15.threeSum2(new int[]{-2,-1,0,1,2},3);
        lists.forEach(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) {
                System.out.println();
                integers.forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.print(integer + ",");
                    }
                });
            }
        });
    }
}