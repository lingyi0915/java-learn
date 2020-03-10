package com.hjh.interview;

/**
 * @author: hjh
 * @description:
 */
public class ProcessTime {
  public static void process(Process process) {
    long st = System.currentTimeMillis();
    process.process();
    long et = System.currentTimeMillis();
    System.out.println("耗时:" + (et - st));
  }

  public static void process(Process process, String prefix) {
    long st = System.currentTimeMillis();
    process.process();
    long et = System.currentTimeMillis();
    System.out.println(prefix + " 耗时:" + (et - st));
  }
}
