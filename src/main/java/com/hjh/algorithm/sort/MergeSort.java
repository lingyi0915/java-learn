package com.hjh.algorithm.sort;

import java.util.Arrays;

/**
 * @author hjh
 * @date 2020/2/17
 * 归并排序 分治
 * 长度为1 的数组,自然有序
 * 长度为2的 比较一次
 * 两个长度为2的进行合并 双指针合并
 *
 */
public class MergeSort {

    private static void sort(int[] nums, boolean isAsc) {
        mergeSort(nums,0,nums.length - 1, isAsc);
    }

    private static void mergeSort(int[] nums, int l, int r, boolean isAsc) {
        if(l == r) {
            return ;
        }
        int mid = (l + r) / 2;
        mergeSort(nums,l,mid,isAsc);
        mergeSort(nums,mid + 1 , r,isAsc);
        merge(nums,l,mid,r,isAsc);
    }

    /**
     * 合并 l->mid,mid+1->r的两个有序数组
     */
    private static void merge(int[] nums, int l, int mid, int r, boolean isAsc) {
        // 直接有序 省略掉
        if(compare(nums,mid,mid+1,isAsc)) {
            return ;
        }
        // 临时数组
        int[] temp = new int[r - l + 1];
        int i = 0;
        int pL = l;
        int pR = mid+1;
        while(pL <= mid && pR <= r) {
            temp[i++] = compare(nums,pL,pR,isAsc) ? nums[pL++] : nums[pR++];
        }
        while(pL <= mid) {
            temp[i++] = nums[pL++];
        }
        while(pR <= r) {
            temp[i++] = nums[pR++];
        }
        for(i = 0 ; i < temp.length ; i++) {
            nums[l+i] = temp[i];
        }
    }

    private static boolean compare(int[] nums, int i, int j, boolean isAsc) {
        if(isAsc) {
            return nums[i] < nums[j];
        } else {
            return nums[i] > nums[j];
        }
    }

    public static void main(String[] args) {
        //
        int [] nums = {1,100,2,99,3,98,4,97};
        MergeSort.sort(nums,true);
        System.out.println(Arrays.toString(nums));
        MergeSort.sort(nums,false);
        System.out.println(Arrays.toString(nums));
    }
}
