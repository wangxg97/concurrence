package com.shadow.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "enjoy")
public class LockTest5 {
    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock=new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.debug("t1 启动...");
            try {
                if (!lock.tryLock(4, TimeUnit.SECONDS)) {
                    log.debug("拿不到锁，返回");
                } else {
                    try {
                        log.debug("获得了锁");
                    } finally {
                        lock.unlock();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        lock.lock();
        log.debug("主线程获得了锁");
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        lock.unlock();
    }
}
