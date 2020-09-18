/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/16 9:58
 * @description ：锁
 */
@Slf4j(topic = "enjoy")
public class EnjoyLock implements Lock {

    EnjoySync enjoySync=new EnjoySync();

    //加锁--阻塞
    @Override
    public void lock() {
        enjoySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return enjoySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        enjoySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        EnjoyLock lock=new EnjoyLock();
        lock.lock();
        TimeUnit.SECONDS.sleep(2);
        log.debug("main");
        lock.unlock();

        new Thread(()->{
            lock.lock();
            log.debug("t1");
            lock.unlock();
        },"t1").start();
    }
}
