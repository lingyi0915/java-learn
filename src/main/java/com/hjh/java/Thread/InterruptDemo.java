package com.hjh.java.Thread;

/**
 * @author: hjh
 * @description:
 */
public class InterruptDemo {
  private static int i = 0;

  public static void main(String[] args) {

    Thread thread =
        new Thread(
            () -> {
              while (!Thread.currentThread().isInterrupted()) {
                i++;
              }
              System.out.println("中断:" + i);
            });

    thread.start();

    try {
      Thread.sleep(1000);
      thread.interrupt();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
