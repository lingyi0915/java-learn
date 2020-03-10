package com.hjh.java.main;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/** Created with IntelliJ IDEA. @Author: 黄俊辉 @Create: 2018-02-06 @Description: */
public class StaticClass {
  static {
    init();
  }

  String str = "";

  public static void init() {

    System.out.println("123456");
  }

  public static void main(String[] args) {
    HashMap<String,String> map = new HashMap<>();
    ConcurrentHashMap map2 = new ConcurrentHashMap();
  }
}
