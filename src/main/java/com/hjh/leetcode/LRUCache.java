package com.hjh.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 执行用时 :160 ms, 在所有 Java 提交中击败了37.10%的用户 内存消耗 :61.7 MB, 在所有 Java 提交中击败了71.50%的用户 */
public class LRUCache {
    Node head = null;
    Node last = null;
    int size = 0;
    int capacity;
    Map<Integer, Node<Integer, Integer>> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        int res = -1;
        if (map.containsKey(key)) {
            // 得到该节点
            res = map.get(key).value;
            // 该节点最近使用过，放到头部去
            tohead(map.get(key));
        }
        return res;
    }

    public void tohead(Node node) {
        if (node == head) {
            return;
        }
        if (node == last) {
            last = node.prev;
        } else {
            // 后接前
            node.next.prev = node.prev;
        }
        // 交换node 和 head 节点
        // 前接后 不是head 必定有前
        node.prev.next = node.next;

        // 插入到head前面
        node.next = head;
        head.prev = node;
        // 成为head 前置空
        node.prev = null;
        // 指针变换
        head = node;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            // 存在 置顶
            tohead(node);
            return;
        }
        // 删除，增加节点
        if (size >= capacity) {
            // 删除最后一个节点
            deleteNode(last);
        }
        // 置顶新节点
        addNode(new Node(key, value));
    }

    public void addNode(Node<Integer, Integer> node) {
        // 如果是空队列
        if (size == 0) {
            head = node;
            last = head;
            map.put(node.key, node);
            size++;
            return;
        }
        // 非空队列，增加新节点
        // 创建新的节点
        node.next = head;
        // 将当前节点放到第一个
        head.prev = node;
        head = node;
        map.put(node.key, node);
        size++;
    }

    public Node deleteNode(Node node) {
        if (last == node) {
            last = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        // 将当前节点脱离双向队列
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        node.prev = null;
        node.next = null;
        map.remove(node.key);
        size--;
        return node;
    }
}

class Node<K, V> {
    K key;
    V value;
    Node next = null;
    Node prev = null;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + "," + value + ')';
    }
}

/** 执行用时 :191 ms, 在所有 Java 提交中击败了20.75%的用户 内存消耗 :65.7 MB, 在所有 Java 提交中击败了53.42%的用户 */
class LRUCache2 {
    int capacity;
    List<Node> list;
    Map<Integer, Node<Integer, Integer>> map;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        //        list = new LinkedList<>();//384ms
        list = new ArrayList<>(); // 191ms
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        int res = -1;
        if (map.containsKey(key)) {
            // 得到该节点
            res = map.get(key).value;
            // 该节点最近使用过，放到头部去
            tohead(map.get(key));
        }
        System.out.println(res);
        return res;
    }

    public void tohead(Node node) {
        list.remove(node);
        // 新节点加到最后
        list.add(node);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            // 存在 置顶
            tohead(node);
            return;
        }
        // 删除，增加节点
        if (list.size() >= capacity) {
            // 删除第一个节点
            Node node = list.remove(0);
            map.remove(node.key);
        }
        // 置顶新节点
        Node node = new Node(key, value);
        list.add(node);
        map.put(key, node);
    }
}
