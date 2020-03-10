package com.hjh.designpattern.factory.base;

/**
 * @author: hjh
 * @description:
 */
public class TeLunSuMilk implements Milk {
  @Override
  public void getMilk() {
    System.out.println("一个特仑苏牛奶");
  }
}
