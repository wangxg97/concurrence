/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.teacher.lock;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/17 13:24
 * @description ：读写锁
 * <p>
 * 读读并发
 * 读写互斥
 * 写写互斥
 * 读锁不支持条件
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁与读锁之前不互斥，所以t1 t3 会并发执行，但是读写锁是互斥的，所以t2必须在 t1 t3完全执行完之后或者执行之前执行，交替执行
 */
@Slf4j(topic = "enjoy")
public class LockTest10 {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        //读
        new Thread(() -> {

            readLock.lock();
            log.debug("read 获取锁");
            try {
                for (int i = 0; i < 10; i++) {
                    m1(i);
                }
            } finally {
                readLock.unlock();
            }
        }, "t1").start();

        //读
        new Thread(() -> {

            readLock.lock();
            log.debug("read 获取锁");
            try {
                for (int i = 0; i < 20; i++) {
                    m1(i);
                }
            } finally {
                readLock.unlock();
            }
        }, "t3").start();

        //写
        new Thread(() -> {

            writeLock.lock();
            log.debug("write 获取锁");
            try {
                for (int i = 0; i < 20; i++) {
                    m1(i);
                }
            } finally {
                writeLock.unlock();
            }
        }, "t2").start();


    }

    public static void m1(int i) {
        log.debug(Thread.currentThread().getName() + "exe" + i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}