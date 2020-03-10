package com.hjh.designpattern.prototype.deep;

import java.util.Date;

/**
 * @author: hjh
 * @description:
 */
public class SunWuKong extends Monkey implements Cloneable {
  public SunWuKong() {
    this.birthday = new Date();
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
