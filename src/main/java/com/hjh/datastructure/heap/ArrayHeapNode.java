package com.hjh.datastructure.heap;

import java.util.Arrays;

/**
 * @author hjh
 * @date 2020/2/17
 */
class ArrayHeapNode {
    private Node [] heap;
    private int size = 0;
    private int capacity;
    private boolean isMinHeap;

    private static int DEFAULT_CAPACITY = 16;

    public ArrayHeapNode() {
        this(DEFAULT_CAPACITY,false);
    }

    public ArrayHeapNode(boolean isMinHeap) {
        this(DEFAULT_CAPACITY,isMinHeap);
    }

    public ArrayHeapNode(int capacity, boolean isMinHeap) {
        this.heap = new Node[capacity];
        this.capacity = capacity;
        this.isMinHeap = isMinHeap;
    }

    int parent(int index) {
        return (index - 1) /2;
    }

    int left(int parent) {
        return parent * 2 + 1;
    }

    public void add(Node val) {
        if(size == capacity) {
            resize(capacity * 2);
        }
        heap[size++] = val;
        siftUp(size-1);
    }

    private void resize(int newCapacity) {
        this.capacity = newCapacity;
        this.heap = Arrays.copyOf(heap,newCapacity);
    }

    /**
     *  数据从下到上平衡 用于新增节点
     */
    private void siftUp(int idx) {
        int p;
        // 如果当前值 > 父节点 交换
        while(idx > 0 && compare(idx,(p = parent(idx)))) {
            swap(p,idx);
            idx = p;
        }
    }

    public Node findTop() {
        if(size == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return heap[0];
    }

    /**
     * 弹出最大值
     */
    public Node poll() {
        if(size == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        swap(0,size-1);
        size--;
        siftDown(0);
        return heap[size];
    }

    public void replaceTop(Node val) {
        heap[0] = val;
        siftDown(0);
    }

    public int size() {
        return size;
    }

    /**
     * 从上到下平衡
     */
    private void siftDown(int idx) {
        int l;
        // 左节点在数组范围内
        while((l = left(idx)) < size) {
            // 右节点在数组范围内 右节点 > 左节点
            if(l+1 < size && compare(l+1,l)) {
                l = l+1;
            }
            // 如果最大的节点 > 父节点
            if(compare(l,idx)) {
                swap(l,idx);
            }
            idx = l;
        }
    }


    private boolean compare(int i , int j) {
        if(isMinHeap) {
            return heap[i].val < heap[j].val;
        } else {
            return heap[i].val > heap[j].val;
        }
    }

    private void swap(int i,int j) {
        Node t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    class Node{
        int val;
        int index;
    }
}
