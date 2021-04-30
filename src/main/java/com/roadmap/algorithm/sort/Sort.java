package com.roadmap.algorithm.sort;

/**
 * 排序算法
 */
public class Sort {
    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public int[] bubbleSort(int[] arr) {
        int len = arr.length;
        //比较len-1轮
        for (int i = 0; i < len - 1; i++) {
            //从0开始两辆比较,移动最大的元素至末尾
            //进行了i轮则最后i个元素有序无需比较
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     *
     * @param arr
     * @return
     */
    public int[] selectionSort(int[] arr) {
        int len = arr.length;
        //curr<=i区域为已排序区域,i为已排序区域末尾
        for (int i = 0; i < len - 1; i++) {
            //设置已排序末尾元素
            int min = i;
            //未排序区域跟已排序末尾元素比较,找出未排序区域最小元素
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //如果找到了最小元素,则将最小元素添加到末尾
            if (i != min) {
                swap(arr, i, min);
            }
        }
        return arr;
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
