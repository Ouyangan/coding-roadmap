package com.roadmap.algorithm.leetcode;

import java.util.PriorityQueue;

public class MergeKLists23 {

    //链表有序
    //定义小顶堆,存储所有链表头节点
    //最小值即为堆顶节点
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            queue.add(node);
        }
        ListNode dummy = new ListNode(-1);
        //定义指针
        ListNode curr = dummy;
        while (!queue.isEmpty()) {
            //建立链表关联
            curr.next = queue.poll();
            //指向下一节点
            curr = curr.next;
            //当前下一节点入堆
            if (curr.next != null) {
                queue.add(curr.next);
            }
        }
        return dummy.next;
    }
}
