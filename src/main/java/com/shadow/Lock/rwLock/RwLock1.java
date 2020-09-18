/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */
package com.shadow.Lock.rwLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：wangxg
 * @version ：
 * @program ：concurrence
 * @date ：Created in 2020/9/18 11:07
 * @description ：写锁上锁流程
 */
@Slf4j(topic = "enjoy")
public class RwLock1 {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        new Thread(()->{
            writeLock.lock();
            try {
                log.debug("T1 加锁成功");
            }finally {
                writeLock.unlock();
            }

        },"t1").start();

    }
}
