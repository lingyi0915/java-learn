package com.hjh.designpattern.prototype;

/**
 * @author: hjh
 * @description:
 */
public class CloneTestMain {
  public static void main(String[] args) {
    Person person = new Person();
    person.setName("person1");
    person.getList().add("item1");

    try {
      Person obj = (Person) person.clone();
      System.out.println(obj);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
  }
}
