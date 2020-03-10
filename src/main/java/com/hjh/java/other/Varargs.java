package com.hjh.java.other;

/** Created with IntelliJ IDEA. @Author: 黄俊辉 @Create: 2018-04-20 @Description:可变参数 */
public class Varargs {
  public static void main(String[] args) {
    Varargs v = new Varargs();
    v.test("abc", "bcd", "efg");
  }

  public void test(String... objs) {
    for (String obj : objs) {
      System.out.println(obj);
    }
  }
}
