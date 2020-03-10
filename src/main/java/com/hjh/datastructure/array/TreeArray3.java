package com.hjh.datastructure.array;

/**
 * @author hjh
 * @date 2020/2/14
 * 树状数组
 * 解决区间更新,区间查询问题
 * 效率: 修改 logn
 *      查询 logn
 *
 *  需要两个树状数组
 */
public class TreeArray3 {

    int size = 0;
    int[] arr;
    int[] arr2;

    public TreeArray3(int[] original) {
        this.size = original.length;
        arr = new int[size];
        arr2 = new int[size];
        add(0,original[0]);
        for(int i = 1 ; i < size ; i++) {
            add(i,original[i] - original[i-1]);
        }
    }

    public int lowbit(int x) {
        return x & (-x);
    }

    // 单点增加
    public void add(int i,int x) {
        int pos = i;
        while(i < size) {
            arr[i] += x;
            arr2[i] += x * (pos - 1);
            i += lowbit(i+1);
        }
    }

    // 单点更新
    public void updateVal(int i , int x) {
        add(i,i,x - getVal(i));
    }

    // 区域增加
    public void add(int i,int j,int x) {
        add(i,x);
        add(j+1,-x);
    }

    // 单点查询
    public int getVal(int i) {
        int res = 0;
        while(i >= 0) {
            res += arr[i];
            i -= lowbit(i + 1);
        }
        return res;
    }

    // 区域求和
    public int sum(int i) {
        int res = 0, x = i;
        while(i > 0){
            res += x * arr[i] - arr2[i];
            i -= lowbit(i+1);
        }
        return res;
    }

    // 区域求和
    public int sum(int i, int j) {
        return sum(j) - sum(i-1);
    }

    public void print() {
        for(int i = 0 ; i < size ; i++) {
            System.out.print(getVal(i) + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        TreeArray3 tr2 = new TreeArray3(arr);
        tr2.print();
        tr2.add(3,4,10);
        tr2.print();
        System.out.println(tr2.sum(3,4));
    }
}
