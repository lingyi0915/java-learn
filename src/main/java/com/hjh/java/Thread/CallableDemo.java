package com.hjh.java.Thread;

import java.util.concurrent.*;

/**
 * @author: hjh
 * @description: callable demo
 */
public class CallableDemo implements Callable<String> {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ThreadPoolExecutor service =
        new ThreadPoolExecutor(
            1,
            1,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1),
            Executors.defaultThreadFactory());

    Future<String> res = service.submit(new CallableDemo());

    System.out.println(res.get());

    service.shutdown();
  }

  @Override
  public String call() throws Exception {

    return "this is a callable demo !";
  }
}
