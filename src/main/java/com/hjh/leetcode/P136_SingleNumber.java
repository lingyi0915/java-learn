package com.hjh.leetcode;

import java.util.HashSet;
import java.util.Set;

/** @Author: hjh @Description: */
public class P136_SingleNumber {
  public static void main(String[] args) {
    int[] nums = {4, 1, 1, 2, 2};
    new P136_SingleNumber().singleNumber(nums);
  }

  /**
   * 执行用时 :25 ms, 在所有 Java 提交中击败了15.74%的用户 内存消耗 :45.1 MB, 在所有 Java 提交中击败了5.05%的用户
   *
   * @param nums
   * @return
   */
  public int singleNumber1(int[] nums) {
    int len = nums.length;
    Set<Integer> set = new HashSet<>(len);
    for (int i = 0; i < len; i++) {
      if (set.contains(nums[i])) {
        set.remove(nums[i]);
      } else {
        set.add(nums[i]);
      }
    }
    return (int) set.toArray()[0];
  }

  public int singleNumber(int[] nums) {
    int res = nums[0];
    for (int i = 1; i < nums.length; i++) {
      res ^= nums[i];
    }
    return res;
  }
}
