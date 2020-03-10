package com.hjh.designpattern.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hjh
 * @description:
 */
public class Person implements Cloneable {
  private String name;
  private List list = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + ", list=" + list + '}';
  }
}
