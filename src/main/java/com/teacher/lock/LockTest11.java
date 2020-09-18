/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.teacher.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/17 14:01
 * @description ：读写锁支持重入但是只支持降级不支持升级
 *
 * 读写锁为什么不能升级
 *      主要是如果有t1 t2 t3 线程同时先获取读锁（因为读锁不是互斥的，所以可以同时获取到读锁），然后到了写锁，假如
 *      t2获取到了写锁，t1获取了读锁还在执行业务代码，这样就造成了读写不互斥，所以读写锁不能升级
 *
 * 读写锁为什么能降级
 *      因为只有一个线程可以获取到锁（读写互斥，写写互斥），所以下面的读锁也之后一个线程可以获取到
 *
 */
@Slf4j(topic = "enjoy")
public class LockTest11 {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        //锁降级 先获取写锁在获取读锁
//        new Thread(()->{
//            writeLock.lock();
//            try {
//                log.debug("write 已经获取");
//                readLock.lock();
//                log.debug("read 已经获取");
//            } finally {
//                readLock.unlock();
//                writeLock.unlock();
//            }
//
//        },"t1").start();

        //锁升级 先获取读锁在获取写锁 不支持
        new Thread(()->{
            readLock.lock();
            try {
                log.debug("read 已经获取");
                writeLock.lock();
                log.debug("write 已经获取");
            } finally {
                readLock.unlock();
                writeLock.unlock();
            }

        },"t1").start();
    }
}
