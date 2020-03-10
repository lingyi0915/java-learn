package com.hjh.designpattern.singleton;

/**
 * @author: hjh
 * @description: 懒汉式单例 用的时候加载
 */
public class LazySingleton {
    private static LazySingleton instance = null;;

private LazySingleton() {}

  /**
   * 线程不安全
   *
   * @return
   */
  public static LazySingleton getInstance() {
    // 这里存在多线程问题
    if (instance == null) {
      instance = new LazySingleton();
    }
    return instance;
  }

  /**
   * 采用.Double CheckLock实现单例：DCL也就是双重锁判断机制 DCL+jvm 存在问题，因为jvm优化 线程拥有本地副本,需要同步到主存，这个过程可能会有问题。
   * 概率太低，不考虑 线程安全 使用synchronized 因为只初始化一次，效率也ok
   *
   * @return
   */
  public static LazySingleton getInstance2() {
    // 这里存在多线程问题
    if (instance == null) {
      // 这里给class加锁,为什么
      synchronized (LazySingleton.class) {
        // 为什么不给instance加锁
        //            synchronized (instance) {
        if (instance == null) {
          instance = new LazySingleton();
        }
      }
    }
    return instance;
  }
}
