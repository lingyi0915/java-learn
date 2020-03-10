package com.hjh.java.gc;

/** @Author: hjh @Create: 2019/6/4 @Description: */
public class FinalizeEscapeGC {

  public static FinalizeEscapeGC SAVE_HOOK = null;

  public static void main(String[] args) {
    SAVE_HOOK = new FinalizeEscapeGC();

    // 对象第一次成功拯救自己
    SAVE_HOOK = null;
    System.gc();
    try {
      Thread.sleep(500);
      if (SAVE_HOOK == null) {
        SAVE_HOOK.isAlive();
      } else {
        System.out.println("SAVE_HOOK is dead!");
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // 对象第二次拯救自己失败，任何对象都只会调用一次finalize()方法
    // 疑问，怎么实现只调用一次finalize的 计数器？标记位？
    // 解答 标记位 每个对象都有标记位 finalize 标记位 和 可达标记位
    SAVE_HOOK = null;
    System.gc();
    try {
      Thread.sleep(500);
      if (SAVE_HOOK == null) {
        SAVE_HOOK.isAlive();
      } else {
        System.out.println("SAVE_HOOK is dead!");
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void isAlive() {
    System.out.println("is alive!");
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("finalize 被调用");
    // 将没有引用的引用一次，被复活了
    FinalizeEscapeGC.SAVE_HOOK = this;
  }
}
