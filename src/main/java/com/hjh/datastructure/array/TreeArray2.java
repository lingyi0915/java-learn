package com.hjh.datastructure.array;

/**
 * @author hjh
 * @date 2020/2/14
 * 树状数组
 * 解决区间更新,单点查询问题
 * 效率: 修改 logn
 *      查询 logn
 */
public class TreeArray2 {

    int size = 0;
    int[] arr;

    public TreeArray2(int[] original) {
        this.size = original.length;
        arr = new int[size];
        add(0,original[0]);
        for(int i = 1 ; i < size ; i++) {
            add(i,original[i] - original[i-1]);
        }
    }

    public int lowbit(int x) {
        return x & (-x);
    }

    public void add(int i,int x) {
        while(i < size) {
            arr[i] += x;
            i += lowbit(i+1);
        }
    }

    public void update(int i,int j,int x) {
        add(i,x);
        add(j+1,-x);
    }

    public int getVal(int i) {
        int res = 0;
        while(i >= 0) {
            res += arr[i];
            i -= lowbit(i + 1);
        }
        return res;
    }

    public void print() {
        for(int i = 0 ; i < size ; i++) {
            System.out.print(getVal(i) + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        TreeArray2 tr2 = new TreeArray2(arr);
        tr2.print();
        tr2.update(3,4,10);
        tr2.print();
    }
}
