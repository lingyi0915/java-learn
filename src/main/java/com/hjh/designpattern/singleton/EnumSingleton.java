package com.hjh.designpattern.singleton;

/**
 * @author: hjh
 * @description: 枚举类单例 (线程安全，调用效率高，不能延时加载，可以天然的防止反射和反序列化调用)
 */
public enum EnumSingleton {
  INSTANCE;

  private String name;

  public void oper() {}
}
