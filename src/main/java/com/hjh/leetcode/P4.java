package com.hjh.leetcode;

/**
 * @author hjh
 * @date 2020/1/10
 * 寻找两个有序数组的中位数
 */
public class P4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] A,B;
        if(nums1.length < nums2.length) {
            A = nums1;
            B = nums2;
        } else {
            A = nums2;
            B = nums1;
        }
        int m = A.length, n = B.length;
        int len = m + n;
        int median = (len + 1)/2;
        // 检查 短数组为空的情况
        if(m == 0) {
            return len % 2 == 1 ? B[median - 1] : (B[median - 1] + B[median])/2.0;
        }
        int i = m , j = median - i;
        if(A[i-1] <= B[j]) {
            if (m == n) {
                return (A[m-1]+B[0])*1.0/2;
            } else if(len % 2 == 1){
                return Math.max(A[i-1],B[j-1]);
            } else { // 因为 j < n 所以 B[j]存在
                return (Math.max(A[i-1],B[j-1])+B[j])*1.0 / 2;
            }
        }
        i = 0 ; j = median - i;
        if(B[j-1] <= A[i]) {
            if (m == n) {
                return (A[i]+B[j-1]) * 1.0 / 2 ;
            } else if(len % 2 == 1) {
                return B[j-1];
            } else { // 因为 j < n 所以 B[j]存在
                return (B[j-1]+Math.min(A[i],B[j]))*1.0 / 2;
            }
        }
        i = (1+m)/2;
        int head = 0 , tail = m;
        while(head< i && i < tail) {
            j = median - i;
            if (A[i-1] <= B[j] && B[j-1] <= A[i]) {
                if(len % 2 == 1) {
                    return Math.max(A[i-1],B[j-1]);
                } else {
                    return (Math.max(A[i-1],B[j-1])+Math.min(A[i],B[j]))*1.0/2;
                }
            } else if (A[i-1] > B[j]) {
                tail = i;
            } else if (B[j-1] > A[i]) {
                // B 大 往后退
                head = i;
            }
            i = ( head + tail ) / 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        P4 p4 = new P4();
        System.out.println(p4.findMedianSortedArrays(new int[]{1,3},new int[]{2}));//2
        System.out.println(p4.findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));//2.5
        System.out.println(p4.findMedianSortedArrays(new int[]{},new int[]{1})); // 1;
        System.out.println(p4.findMedianSortedArrays(new int[]{3},new int[]{-2,-1}));// -1
        System.out.println(p4.findMedianSortedArrays(new int[]{3},new int[]{1,2,4}));// -2.5
    }
}
