package com.hjh.designpattern.factory.simplefactory;

import com.hjh.designpattern.factory.base.MengNiuMilk;
import com.hjh.designpattern.factory.base.Milk;
import com.hjh.designpattern.factory.base.TeLunSuMilk;
import com.hjh.designpattern.factory.func.Factory;
import com.hjh.designpattern.factory.func.MengNiuFactory;
import com.hjh.designpattern.factory.func.TeLunSuFactory;

import java.util.HashMap;
import java.util.Map;

/** @Author: hjh @Description: 简单工厂的几种区别: */
public class SimpleFactory {

  static Map<MilkEnum, Milk> milkMap = new HashMap<>();
  /**
   * 总结: 简单工厂模式增加一个类别的时候，需要做两件事: 1.创建一个类别的对象 2.绑定到某个type上 缺点: 1. 创建过程绑定在工厂的get方法中，生产牛奶很简单，
   * 如果是生产汽车，生产计算机，每一种计算机，汽车的生产过程都非常繁琐(初始化cpu，初始化内存，安装操作系统等) 这个get方法将会非常非常长。 2. 对修改没有关闭 3.
   * 每个type不能用来创建多种产品，例如，TELUNSU这个类别需要创建 牛奶，面包，MENGNIU这个类别需要创建牛奶，奶糖，酸奶 4. 每个type创建的milk，需要不同的参数，怎么办？
   * 当前办法，参数必须固定，不灵活。 生产多个商品的时候，需要写多种 getXXX 业务绑定太死了。 可以使用方法工厂。
   *
   * <p>适用范围: 简单工厂模式，适用于简单的对象创建(种类不多，初始化简单)
   */
  static Map<MilkEnum, Factory> milkFactoryMap = new HashMap<>();

  static {
    milkMap.put(MilkEnum.TELUNSU, new TeLunSuMilk());
    milkMap.put(MilkEnum.MENGNIU, new MengNiuMilk());
  }

  static {
    milkFactoryMap.put(MilkEnum.TELUNSU, new TeLunSuFactory());
    milkFactoryMap.put(MilkEnum.MENGNIU, new MengNiuFactory());
  }

  /**
   * 简单工厂根据类型获得牛奶 缺点: 1. 需要去记忆当前工厂提供的牛奶品牌
   *
   * @param type 牛奶品牌
   * @return
   */
  public Milk getMilk1(String type) {
    if ("telunsu".equals(type)) {
      return new TeLunSuMilk();
    } else if ("mengniu".equals(type)) {
      return new MengNiuMilk();
    } else {
      throw new RuntimeException("无法识别的牛奶品牌");
    }
  }

  /**
   * 相比getMilk1 弥补了输入字符串容易出错的点 缺点: 1.假设种类有100种，这里需要100个if 需要把if去掉
   *
   * @param type
   * @return
   */
  public Milk getMilk2(MilkEnum type) {
    if (MilkEnum.TELUNSU == type) {
      return new TeLunSuMilk();
    } else if (MilkEnum.MENGNIU == type) {
      return new MengNiuMilk();
    } else {
      throw new RuntimeException("无法识别的牛奶品牌");
    }
  }

  /**
   * 相比getMilk2 没有了那么多if，只不过是把if转换成了map，做了一个初始化而已
   *
   * <p>上面的static注册代码块，相当于是把业务代码嵌入到了工厂中， 每次增加类，都要到工厂类中修改。违法了六大原则中的开闭原则
   *
   * <p>可以在工厂中提供一个注册方法，然后每次创建的对象中，实现注册方法，将自己注册到工厂中。 这样，所有的业务代码，在新增的类中，就可以完成了，不用改这个工厂的代码逻辑了。
   *
   * <p>实现不修改工厂的基础是: 1.没有if，新增类别不用增加if逻辑，可以自动识别 map.get(); 2.可以在外部新增新的类，通过方法实现就可以。
   *
   * <p>缺点：内存占用，单例对象， 可以通过反射来处理。 初始化繁杂的话 -> 可以放到 new的对象的构造函数中初始化。
   *
   * @param type
   * @return
   */
  public Milk getMilk3(MilkEnum type) {
    if (milkMap.containsKey(type)) {
      return milkMap.get(type);
    } else {
      throw new RuntimeException("无法识别的牛奶品牌");
    }
  }

  /**
   * 相比getMilk3
   *
   * @param type
   * @return
   */
  public Factory getMilkFactory(MilkEnum type) {
    if (milkFactoryMap.containsKey(type)) {
      return milkFactoryMap.get(type);
    } else {
      throw new RuntimeException("无法识别的牛奶品牌");
    }
  }

  /**
   * 方法工厂变得更麻烦了，在milk和生产之间，加入了品牌的工厂 TeLunSuFactory MengNiuFactory 为什么？ 相比getMilk3 解决了问题1
   * 每个工厂自己负责生产对应的milk,不管生产过程如何复杂 当然，也可以把复杂的创建逻辑放到Milk的构造方法中
   *
   * <p>所以，这个例子举得不好，设计模式并不是越复杂越好，方法工厂模式在简单的对象创建的时候，比简单工厂复杂的多。 没有必要用方法工厂。
   *
   * <p>方法工厂的核心思想在于，将复杂的创建逻辑，抽象到品牌对应的工厂中去。 优点: 原来的创建逻辑，放在了getMilk方法中，(new TeLunSuMilk() )
   * 现在，拆开到不同的类中 如果我们要创建新的品牌时，虽然增加的代码变多了 (xxx牛奶 -> xxx牛奶,xxx工厂)
   * 但是，结构上清晰了，xxx工厂处理创建逻辑，这里的简单工厂，创建对象从牛奶变成了牛奶工厂。
   *
   * @param type
   * @return
   */
  public Milk getMilk4(MilkEnum type) {
    Factory factory = getMilkFactory(type);
    Milk milk = factory.getMilk();
    return milk;
  }

  enum MilkEnum {
    TELUNSU,
    MENGNIU;
  }
}
