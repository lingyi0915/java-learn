package com.hjh.interview;

import org.junit.Test;

import java.util.*;

/**
 * @author: hjh
 * @description:
 */
public class ListComp {
  @Test
  public void test() {
    List<String> list = new ArrayList<>(100000 * 100);
    List<String> vector = new Vector<>(100000 * 100);
    List<String> link = new LinkedList<>();

    int n = 100000;
    String[] arr = new String[n];
    ProcessTime.process(
        () -> {
          for (int i = 0; i < n; i++) {
            arr[i] = UUID.randomUUID().toString();
          }
        },
        "数组");

    // 1000w size 4420214 189ms
    threadAddTime(list, arr, "ArrayList threadAddTime");
    //         1000w size 1000W 523 线程安全
    threadAddTime(vector, arr, "Vector threadAddTime");
    //         1000w size 4592927 3666 少了一半
    threadAddTime(link, arr, "LinkedList threadAddTime");

    Object object = new Object();
    String str = "";
    Set<String> set = new HashSet<>();

    // 1000w 221ms
    //        addTime(list,arr,"ArrayList addTime");
    //         1000w 435ms
    //        addTime(vector,arr,"Vector addTime");
    //         1000w 6144
    //        addTime(link,arr,"LinkedList addTime");

    // 1000w 8 ms
    //        getTime(list,"ArrayList getTime");
    // 1000w 591 ms
    //        getTime(vector,"Vector getTime");
    // 10W 5716 n^2上升
    //        getTime(link,"LinkedList getTime");

    // 1000w 14
    //        iteratorTime(list,"ArrayList iteratorTime");
    // 1000w 383
    //        iteratorTime(vector,"Vector iteratorTime");
    // 1000w 0
    //        iteratorTime(link,"LinkedList iteratorTime");

    // 10W 1468
    //        removeTime(list,"ArrayList removeTime");
    // 10W 1074
    //        removeTime(vector,"Vector removeTime");
    // 1000w 109
    //        removeTime(link,"LinkedList removeTime");

  }

  public void addTime(List<String> list, String[] arr, String prefix) {
    ProcessTime.process(
        () -> {
          for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
          }
        },
        prefix);
  }

  public void getTime(List<String> list, String prefix) {
    ProcessTime.process(
        () -> {
          for (int i = 0; i < list.size(); i++) {
            list.get(i);
          }
        },
        prefix);
  }

  public void iteratorTime(List<String> list, String prefix) {
    ProcessTime.process(
        () -> {
          Iterator<String> itor = list.iterator();
          while (itor.hasNext()) {
            itor.next();
          }
        },
        prefix);
  }

  public void removeTime(List<String> list, String prefix) {
    ProcessTime.process(
        () -> {
          Iterator<String> itor = list.iterator();
          while (itor.hasNext()) {
            itor.next();
            itor.remove();
          }
        },
        prefix);
  }

  public void threadAddTime(List<String> list, String[] arr, String prefix) {
    ProcessTime.process(
        () -> {
          int threadCnt = 100;
          Thread[] threads = new Thread[threadCnt];
          for (int i = 0; i < threadCnt; i++) {
            threads[i] =
                new Thread(
                    () -> {
                      for (int j = 0; j < arr.length; j++) {
                        list.add(arr[j]);
                      }
                    });
          }
          for (int i = 0; i < threadCnt; i++) {
            threads[i].start();
          }
          for (int i = 0; i < threadCnt; i++) {
            try {
              threads[i].join();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          System.out.println(prefix + ": size = " + list.size());
        },
        prefix);
  }
}
