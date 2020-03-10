package com.hjh.java.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hjh
 * @date 2019/12/19
 */
public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {

        List<Job> list = new ArrayList<>(10);

        for(int i = 0 ; i < 10 ; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            list.add(job);
            Thread thread = new Thread(job);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;

    list.forEach(job -> System.out.println("Job priority: "+job.priority
                                                   + " job count: "+job.count));
    }

    private static class Job implements Runnable {

        int count = 0;
        int priority;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                count++;
                Thread.yield();
            }
        }
    }
}
