package com.hjh.java.Thread;

/**
 * @author hjh
 * @date 2020/3/9
 */
public class JoinDemo {
  public static void main(String[] args) throws InterruptedException {
    //
      Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
              System.out.println("this is a thread");
          }
      });
      thread.join();
  }
}
