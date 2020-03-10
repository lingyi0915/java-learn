package com.hjh.designpattern.factory.func;

import com.hjh.designpattern.factory.base.Milk;
import com.hjh.designpattern.factory.base.TeLunSuMilk;

/**
 * @author: hjh
 * @description: 特仑苏工厂
 */
public class TeLunSuFactory implements Factory {
  @Override
  public Milk getMilk() {
    Milk milk = new TeLunSuMilk();

    /** 这里可以做其他的处理 */
    System.out.println("特仑苏工厂生产了牛奶");
    milk.getMilk();

    return milk;
  }
}
