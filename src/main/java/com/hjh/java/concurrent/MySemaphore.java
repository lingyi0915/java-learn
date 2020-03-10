package com.hjh.java.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hjh
 * @date 2020/2/17
 * {@link Semaphore}
 */
public class MySemaphore {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        AtomicInteger integer = new AtomicInteger();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock1.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock1.writeLock();
        readLock.lock();
        readLock.unlock();
        writeLock.lock();
        writeLock.unlock();
    }
}
