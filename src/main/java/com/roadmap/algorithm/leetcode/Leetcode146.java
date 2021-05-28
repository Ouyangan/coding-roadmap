package com.roadmap.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode146 {

    private int size;
    private final int capacity;
    private final Map<Integer, Node> cache = new HashMap<>();
    private final Node head;
    private final Node tail;

    public Leetcode146(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        //移动到链表头部
        moveToHead(node);
        return node.val;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void put(int key, int value) {
        //缓存获取key
        Node node = cache.get(key);
        //存在:更新
        //移动到链表头
        if (node != null) {
            node.val = value;
            moveToHead(node);
        } else {
            //不存在:插入,更新size
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            //判断容量,容量达到上限后,移除链表末尾
            if (size > capacity) {
                int removeKey = removeTail();
                cache.remove(removeKey);
                size--;
            }
        }
    }

    private int removeTail() {
        Node removeNode = tail.prev;
        removeNode(removeNode);
        return removeNode.key;
    }

    class Node {
        private final int key;
        private int val;
        private Node prev;
        private Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}

