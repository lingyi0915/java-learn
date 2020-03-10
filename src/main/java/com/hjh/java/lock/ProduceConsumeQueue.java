package com.hjh.java.lock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hjh
 * @date 2019/11/30
 */
public class ProduceConsumeQueue implements Runnable {

  private List<Task> queue = new ArrayList<>();
  private int maxQueueSize = 5;
  private Lock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();
  private Condition wait = lock.newCondition();
  private Object obj = new Object();

  private BlockingQueue<Task> blockingQueue = new ArrayBlockingQueue<>(100);

  public ProduceConsumeQueue() {
    new Thread(new Produce(), "produce").start();
    new Thread(new Consumer(), "consumer").start();
  }

  public static void main(String[] args) {
    new Thread(new ProduceConsumeQueue(), "produceConsumerQueue").start();
  }

  @Override
  public void run() {
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      String msg = "message " + i;
      Task task = new Task(msg, random.nextInt(3000));
      try {
        System.out.println("接收到消息:" + msg);
        blockingQueue.put(task);
        Thread.sleep(random.nextInt(3000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  class Produce implements Runnable {
    @Override
    public void run() {
      while (true) {
        Task task = null;
        try {
          task = blockingQueue.take();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (obj) {
          try {
            if (queue.size() == maxQueueSize) {
              System.out.println("run 队列满了,obj.wait()");
              obj.wait();
              System.out.println("run obj.wait() 被唤醒");
            }
            queue.add(task);
            System.out.println("run 写入任务" + task.name + ", notEmpty.signal()");
            obj.notify();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  class Consumer implements Runnable {
    @Override
    public void run() {
      while (true) {
        lock.lock();
        try {
          if (queue.size() == 0) {
            System.out.println("finish notEmpty.await();");
            notEmpty.await();
            System.out.println("finish notEmpty.await() 被唤醒");
          }
          while (true) {
            Iterator<Task> iterator = queue.iterator();
            while (iterator.hasNext()) {
              Task task = iterator.next();
              if (task.isFinish()) {
                System.out.println(task.name + " finish notFull signal");
                iterator.remove();
                notFull.signal();
              }
            }
            System.out.println("finish notEmpty await 1000");
            notEmpty.await(1000, TimeUnit.MILLISECONDS);
            System.out.println("finish notEmpty await 1000 被唤醒");
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
      }
    }
  }

  class Task {
    String name;
    long createTime;
    long interval;

    public Task(String name, long interval) {
      this.name = name;
      this.createTime = System.currentTimeMillis();
      this.interval = interval;
    }

    public boolean isFinish() {
      return System.currentTimeMillis() - createTime >= interval;
    }
  }
}
