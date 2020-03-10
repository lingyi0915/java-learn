package com.hjh.datastructure.array;

/**
 * @author hjh
 * @date 2020/2/14
 * 树状数组
 * 解决单点更新,区间求和问题
 * 效率: 修改 logn
 *      查询 logn
 * 优点: 能解决的问题比线段树快一些
 * 缺点: 功能有限
 */
public class TreeArray {

    int size = 0;
    int[] arr;
    int[] original;

    public TreeArray(int[] original) {
        this.size = original.length;
        arr = new int[size];
        this.original = original;
        build();
    }

    public void build() {
        for(int i = 0 ; i < size ; i++) {
            add(i,original[i]);
        }
    }

    public int lowbit(int x) {
        // 2^k  k 表示 x 后面有多少个0
        // 即保留 x 最右边一位1 其余位置0
        return x & (-x);
    }

    public int lowbit2(int x) {
        return x - (x & (x - 1));
    }

    public void add(int i,int x) {
        while(i < size) {
            arr[i] += x;
            i += lowbit(i+1);
        }
    }



    public void update(int i,int x) {
        add(i,x - original[i]);
        original[i] = x;
    }

    public int sum(int i) {
        int res = 0;
        while(i >= 0) {
            res += arr[i];
            i -= lowbit(i + 1);
        }
        return res;
    }

    public int sum(int i,int j) {
        return sum(j) - sum(i-1);
    }

    public void print() {
        for(int i = 0 ; i < size ; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int [] arr = {7,2,7,2,0};
        TreeArray treeArray = new TreeArray(arr);
        treeArray.print();
        treeArray.update(4,6);
        treeArray.print();
        treeArray.update(0,2);
        treeArray.print();
        treeArray.update(0,9);
        treeArray.print();
        System.out.println(treeArray.sum(4,4));
        treeArray.update(3,8);
        treeArray.print();
        System.out.println(treeArray.sum(0,4));
        treeArray.update(4,1);
        treeArray.print();
        System.out.println(treeArray.sum(0,3));
        System.out.println(treeArray.sum(0,4));
        treeArray.update(0,4);
        treeArray.print();
    }
}
