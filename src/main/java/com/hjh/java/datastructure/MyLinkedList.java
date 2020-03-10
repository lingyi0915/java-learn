package com.hjh.java.datastructure;

/** @Author: hjh @Create: 2019/6/5 @Description: 纯单向链表 */
public class MyLinkedList<V> {

  private Node head;
  private Node last;
  private int size;

  public int size() {
    return size;
  }

  public void add(V value) {
    Node node = new Node(value);
    this.last.next = node;
    this.last = node;
  }

  public void remove(V value) {
    Node node = new Node(value);
    this.last.next = node;
    this.last = node;
  }

  class Node {
    V value;
    Node next;

    public Node(V value) {
      this.value = value;
    }
  }
}
