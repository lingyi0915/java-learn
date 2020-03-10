package com.hjh.designpattern.proxy;

public class Target implements TargetInterface {
  @Override
  public void method1() {
    System.out.println("method1 running ...");
  }

  @Override
  public void method2() {
    System.out.println("method2 running ...");
  }

  @Override
  public int method3(Integer i) {
    System.out.println("method3 running ...");
    return i;
  }
}
