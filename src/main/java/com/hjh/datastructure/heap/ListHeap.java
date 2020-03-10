package com.hjh.datastructure.heap;

import java.util.PriorityQueue;

/**
 * @author hjh
 * @date 2020/2/17
 */
public class ListHeap {
  public static void main(String[] args) {
    //
      PriorityQueue<Integer> queue = new PriorityQueue<>();

      queue.add(1);
      queue.add(2);
      queue.remove(1);

      System.out.println(queue.poll());
  }
}
