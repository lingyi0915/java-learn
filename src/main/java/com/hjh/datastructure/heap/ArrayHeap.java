package com.hjh.datastructure.heap;

import java.util.Arrays;

/**
 * @author hjh
 * @date 2020/2/17
 * 数组堆
 * 从1开始存储 0不存储
 * left - > parent index / 2
 * right -> parent (index - 1)/ 2 -> index / 2
 * parent -> left index * 2         偶数
 * paretn -> right index * 2 + 1    奇数
 *
 * 从 0 开始存储
 * left -> parent (index - 1) / 2
 * right -> parent (index - 2) / 2 -> (index - 1) / 2;
 * parent -> left index * 2 + 1
 * parent -> right index * 2 + 2
 *
 * 堆是一颗完全二叉树
 *
 * 增加节点时:
 * 1.维持完全二叉树,只能添加叶子节点
 * 2.维持根节点最大/最小
 * 实现逻辑:
 *  1.在叶子节点后面增加一个新叶子
 *  2.比较叶子和父亲,父亲大则交换 直到该叶子没有父亲大
 *  3.比较该节点的大小和左右子树的大小,和大的进行交换,直到比左右都大
 *
 * 弹出根节点:
 * 1.将根节点和最后一个元素换位置 堆大小-1
 * 2.从根开始向下遍历,找到左右子树的最大值(大于自己)进行交换,直到自己>左右子节点
 *
 */
public class ArrayHeap {
    private int [] heap;
    private int size = 0;
    private int capacity;
    private boolean isMinHeap;

    private static int DEFAULT_CAPACITY = 16;

    public ArrayHeap() {
        this(DEFAULT_CAPACITY,false);
    }

    public ArrayHeap(boolean isMinHeap) {
        this(DEFAULT_CAPACITY,isMinHeap);
    }

    public ArrayHeap(int capacity, boolean isMinHeap) {
        this.heap = new int[capacity];
        this.capacity = capacity;
        this.isMinHeap = isMinHeap;
    }

    int parent(int index) {
        return (index - 1) /2;
    }

    int left(int parent) {
        return parent * 2 + 1;
    }

    public void add(int val) {
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

    public int findTop() {
        if(size == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return heap[0];
    }

    /**
     * 弹出最大值
     */
    public int poll() {
        if(size == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        swap(0,size-1);
        size--;
        siftDown(0);
        return heap[size];
    }

    public void replaceTop(int val) {
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
            return heap[i] < heap[j];
        } else {
            return heap[i] > heap[j];
        }
    }

    private void swap(int i,int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public static void main(String[] args) {
        ArrayHeap heap = new ArrayHeap(1,false);
        heap.add(0);
        heap.replaceTop(1);
        heap.replaceTop(2);
        heap.replaceTop(1);
        heap.replaceTop(8);
        System.out.println(heap.poll());
    }

}
