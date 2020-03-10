package com.hjh.designpattern.factory.func;

import com.hjh.designpattern.factory.base.MengNiuMilk;
import com.hjh.designpattern.factory.base.Milk;

/**
 * @author: hjh
 * @description: 蒙牛工厂
 */
public class MengNiuFactory implements Factory {
  @Override
  public Milk getMilk() {
    Milk milk = new MengNiuMilk();

    /** 这里可以做其他的处理 */
    System.out.println("蒙牛工厂生产了牛奶");
    milk.getMilk();

    return milk;
  }
}
