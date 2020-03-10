package com.hjh.java.gc;

/** @Author: hjh @Create: 2019/6/4 @Description: 主动调用gc,然后finalize中不回收 */
public class BookGC {

  String name;
  boolean checkOut = false;

  public BookGC(boolean checkOut) {
    this.checkOut = checkOut;
  }

  public BookGC(String name, boolean checkOut) {
    this.name = name;
    this.checkOut = checkOut;
  }

  /**
   * 每次运行结果不一，因为System.gc()不一定就一定触发gc 可能直接jvm结束都没有触发gc 尝试sleep(1000);每次都会触发gc()
   *
   * @param args
   */
  public static void main(String[] args) {
    BookGC bookGC = new BookGC("有引用", true);
    bookGC.checkIn();
    bookGC = null; // 释放引用对象 依然不会被第一时间回收 如果有引用可能永远不会被回收
    new BookGC("无引用-可回收", true).checkIn();
    new BookGC("无引用-不可回收", true);
    System.gc();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void checkIn() {
    checkOut = false;
  }

  @Override
  protected void finalize() throws Throwable {
    System.out.println(name + ":触发一次GC");
    if (checkOut) {
      System.out.println(name + ":未回收");
    } else {
      System.out.println(name + ":已回收");
      super.finalize();
    }
  }
}
