package com.hjh.leetcode;

/** @Author: hjh @Description: */
public class P7_reverse {
  public static void main(String[] args) {
    int a = new P7_reverse().reverse(-123);
    System.out.println(a);
  }

  public int reverse(int x) {
    int i = 0, j = 0;
    long res = 0;
    while (x != 0) {
      res = res * 10 + x % 10;
      x /= 10;
    }
    if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
      return 0;
    }
    return (int) res;
  }
}
