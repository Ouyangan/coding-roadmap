package com.roadmap.algorithm.leetcode;

public class Leetcode160 {
    /**
     * 遍历A+B 和 B+A 长度是相同的,假如有公共节点的话AB一定会同时到达相交节点,此时跳出循环
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != pb) {
            if (pa != null) {
                pa = pa.next;
            } else {
                pa = headB;
            }
            if (pb != null) {
                pb = pb.next;
            } else {
                pb = headA;
            }
        }
        return pa;
    }
}
