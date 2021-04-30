package com.roadmap.algorithm.sort;

import org.junit.jupiter.api.Test;

class SortTest {
    private int[] createArr() {
        int[] arr = {5, 3, 8, 7, 3, 6, 1};
        printArr(arr);
        return arr;
    }

    @Test
    void bubbleSort() {
        int[] arr = createArr();
        Sort sort = new Sort();
        int[] ints = sort.bubbleSort(arr);
        printArr(ints);

    }

    private void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();

    }
}