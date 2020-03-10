package com.hjh.leetcode;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/** @Author: hjh @Description: */
public class P146_LRU {

  public static LRUCache2 cache;

  public static void test(String[] funs, int[][] inputs) {
    try {
      Class clazz = Class.forName("com.hjh.leetcode." + funs[0]);
      Constructor constructor = clazz.getConstructor(int.class);
      Object obj = constructor.newInstance(inputs[0][0]);

      for (int i = 1; i < funs.length; i++) {
        String fun = funs[i];
        int[] input = inputs[i];
        System.out.println(Arrays.toString(input));
        Class<Integer>[] typeClass = new Class[input.length];
        for (int j = 0; j < input.length; j++) {
          typeClass[j] = int.class;
        }
        Method method = clazz.getDeclaredMethod(fun, typeClass);
        System.out.println(method);
        int res = (Integer) method.invoke(obj, new Object[] {input});
        System.out.println(res);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    cache = new LRUCache2(2);
    cache.put(2, 1);
    cache.put(1, 1);
    cache.put(2, 3);
    cache.put(4, 1);
    cache.get(1);
    System.out.println(cache.get(2));
    cache.put(4, 3);
    cache.get(2); // 返回 -1 (未找到)
    cache.get(3); // 返回  3
    cache.get(4); // 返回  4
  }

  public static void printNodes(Node node) {
    System.out.print(node + "->");
    if (node.next != null) {
      printNodes(node.next);
    } else {
      System.out.println();
      System.out.println(cache.map);
      System.out.println("--------------");
    }
  }

  @Test
  public void testRes() {
    String[] funcs = {"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"};
    int[][] inputs = {{2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};
    test(funcs, inputs);
  }

  @Test
  public void test2() {
    cache = new LRUCache2(2);
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);
    cache.put(3, 3);
    cache.get(2);
    cache.put(4, 4);
    cache.get(1);
    cache.get(3);
    cache.get(4);
  }
}
