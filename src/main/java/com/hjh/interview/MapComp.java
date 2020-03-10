package com.hjh.interview;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: hjh
 * @description:
 */
public class MapComp {
  @Test
  public void mapComp() {

    int n = 10000000;

    Map<Integer, String> map = new HashMap<>(n);
    String[] arr = new String[n];
    // 1000W 22171
    ProcessTime.process(
        () -> {
          for (int i = 0; i < n; i++) {
            arr[i] = UUID.randomUUID().toString();
          }
        },
        "数组");

    // 100000 29ms
    // 1000W 883ms
    addTime(map, arr, "HashMap addTime");

    // 100000 19ms
    // 1000W 150
    iteratorTime(map, "HashMap iteratorTime");

    // 100000 13ms
    // 1000W 108
    iteratorEntryTime(map, "HashMap iteratorEntryTime");
  }

  public void addTime(Map<Integer, String> map, String[] arr, String prefix) {
    ProcessTime.process(
        () -> {
          for (int i = 0; i < arr.length; i++) {
            map.put(i, arr[i]);
          }
        },
        prefix);
  }

  public void iteratorTime(Map<Integer, String> map, String prefix) {
    ProcessTime.process(
        () -> {
          for (Integer key : map.keySet()) {
            map.get(key);
          }
        },
        prefix);
  }

  public void iteratorEntryTime(Map<Integer, String> map, String prefix) {
    ProcessTime.process(
        () -> {
          for (Map.Entry<Integer, String> entry : map.entrySet()) {
            entry.getValue();
          }
        },
        prefix);
  }
}
