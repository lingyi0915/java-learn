package com.hjh.designpattern.factory.func;

import com.hjh.designpattern.factory.base.Milk;

/**
 * @author: hjh
 * @description: 方法工厂
 */
public interface Factory {
  public Milk getMilk();
}
