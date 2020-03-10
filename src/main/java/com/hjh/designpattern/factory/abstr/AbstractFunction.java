package com.hjh.designpattern.factory.abstr;

import com.hjh.designpattern.factory.base.MengNiuMilk;
import com.hjh.designpattern.factory.base.Milk;
import com.hjh.designpattern.factory.base.TeLunSuMilk;

/**
 * @author: hjh
 * @description: 抽象工厂
 */
public class AbstractFunction {
  /**
   * @param milkPrice 牛奶价格
   * @return
   */
  public Milk getTeLunSuMilk(String milkPrice) {
    return new TeLunSuMilk();
  }

  /**
   * @param milkName 名称
   * @param milkSize 大小
   * @return
   */
  public Milk getMengNiuMilk(String milkName, String milkSize) {
    return new MengNiuMilk();
  }

  /**
   * 抽象工厂的优点 1.不再需要传入类型参数 2.创建的时候，可以指定不同的参数。
   *
   * <p>缺点: 1.每次增加类型，需要加方法。 但是原来的也需要加枚举类型，或者手动注册到工厂中。 2.不同品牌用于区分方法，多种产品，就多个方法
   *
   * <p>1.不可避免的修改抽象工厂类，但是不需要改变原有的方法，可以接受 2.可以支持不同的参数创建。 3.不用提前创建对象，没有多余内存的问题。
   *
   * <p>致命缺点: 无法通过配置，指定创建类型，如果配置指定，回到简单工厂上。
   */
}
