package com.hjh.designpattern.factory.base;

/**
 * @author: hjh
 * @description:
 */
public class MengNiuMilk implements Milk {
  @Override
  public void getMilk() {
    System.out.println("一个蒙牛牛奶");
  }
}
