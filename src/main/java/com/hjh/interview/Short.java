package com.hjh.interview;

/**
 * @author: hjh
 * @description:
 */
public class Short {
  public static void main(String[] args) {
    short s1 = 1;
    s1 = (short) (s1 + 1);

    System.out.println(s1);

    //        List<Short> list = new ArrayList<Short>();

    short s2 = 1;
    s2 += 1;
    System.out.println(s2);

    //        System.out.println(Integer.MIN_VALUE+","+Integer.MAX_VALUE);
    //        System.out.println(Long.MIN_VALUE+","+Long.MAX_VALUE);

    System.out.println(Math.round(11.5));
    System.out.println(Math.round(-11.5));
    double f = 23 - 11.5;
    System.out.println(f);
  }
}
