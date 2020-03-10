package com.hjh.designpattern.singleton;

/**
 * @author: hjh
 * @description: 饿汉式单例 // 假定必须要使用 直接创建
 *     <p>在java中存在缺陷，可以被反射创建实例
 */
public class HungrySingleton {
    // 静态加载，没有线程安全问题
  // 读取HungrySingleton类的时候就加载了，但是不代表需要这个单例，可能有内存浪费
  private static HungrySingleton instance = new HungrySingleton();;

private HungrySingleton() {}

  public static HungrySingleton getInstance() {
    return instance;
  }

  /**
   * 被反射破解掉了
   *
   * @param args
   */
  public static void main(String[] args) {
    HungrySingleton singleton = HungrySingleton.getInstance();
    System.out.println(singleton);
    HungrySingleton singleton2 = Main.newInStance(HungrySingleton.class);
    System.out.println(singleton2); // 反射破解
  }
}
