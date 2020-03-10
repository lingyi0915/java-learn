package com.hjh.designpattern.singleton;

/**
 * @author: hjh
 * @description:
 *     <p>懒汉式单例的线程安全版本 饿汉式单例的延迟加载版本
 *     <p>线程安全，调用效率高，可以延时加载 不能方法反射和反序列化
 *     <p>我觉得最适合最方便的单例 (虽然会被反射,反序列化,但是，反射单例的概率不大，反射本身也不是想获得单例对象了)
 *     <p>1.单线程保证单例 private 构造方法 2.多线程保证单例 static变量加载class的时候创建，多线程访问的时候，已经不需要创建了
 *     3.避免加载class就自动创建单例浪费内存 使用静态内部类，创建Singleton类的时候,不会创建对象了。
 */
public class Singleton {

  private Singleton() {
    System.out.println("创建对象" + this);
  }

  public static Singleton getInstance() {
    System.out.println("第一次申请对象");
    return InnerClass.instance;
  }

  public static void test() {
    System.out.println("测试静态创建");
  }

  public static void main(String[] args) {
    Singleton singleton = Singleton.getInstance();
    System.out.println(singleton);
    Singleton singleton2 = Main.newInStance(Singleton.class);
    System.out.println(singleton2); // 反射破解
  }

  /** 使用内部类， 只有调用getInstance的时候才会创建对象 */
  private static class InnerClass {
    private static final Singleton instance = new Singleton();
  }
}
